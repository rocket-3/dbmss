/*
 * Copyright (C) 2018-2022 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ru.fusionsoft.database.migration.postgres;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Comparator;
import java.util.Map;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sorted;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.Concatenated;
import org.cactoos.text.Joined;
import org.cactoos.text.Replaced;
import org.cactoos.text.Split;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.migration.diff.SmartIterableTemporalDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.order.OrderOfTableColumn;
import ru.fusionsoft.database.migration.typecasts.SimpleTypecast;
import ru.fusionsoft.database.migration.typecasts.Typecast;
import ru.fusionsoft.database.migration.typecasts.TypecastsOfConfigAndDbms;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.dbms.PostgresDbms;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.text.DbdColumnIdentity;
import ru.fusionsoft.database.text.FixMark;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.database.text.TextOfConditionsLines;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to move data from {@link SwappingEntityNameSuffix}-named brother
 *  to newly created table.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (300 lines).
 * @checkstyle LineLengthCheck (300 lines).
 */
public class PgTableMigrateDataOfSwappingEntitySql implements Text {

    /**
     * The {@link TemporalDiff} of table {@link DbObject}.
     */
    private final TemporalDiff<DbObject<YamlNode>> diff;

    /**
     * The MigrationConfigMapping encapsulated.
     */
    private final MigrationConfigMapping config;

    /**
     * Instantiates a new Pg table migrate data of swapping entity sql.
     * @param diff The {@link TemporalDiff} of table {@link DbObject}.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public PgTableMigrateDataOfSwappingEntitySql(
        final TemporalDiff<DbObject<YamlNode>> diff,
        final MigrationConfigMapping config
    ) {
        this.diff = diff;
        this.config = config;
    }

    @Override
    public final String asString() {
        final Func<DbdColumnMapping, String> dbname = col -> new TextOfObjectField(
            col::asMapping,
            DbdColumnFields.DBNAME
        ).asString();
        final Func<DbdColumnMapping, String> identity = col -> new DbdColumnIdentity(col).asString();
        final Func<DbdColumnMapping, String> type = col -> new TextOfObjectField(
            col::asMapping,
            DbdColumnFields.DBMSTYPE
        ).asString();
        final Iterable<DbdColumnMapping> next = new Sorted<>(
            Comparator.comparing(item -> new OrderOfTableColumn(item).value().intValue()),
            new DbdColumnsOfTable(this.diff.next())
        );
        final Map<String, TemporalDiff<DbdColumnMapping>> both = new MapOf<>(
            (TemporalDiff<DbdColumnMapping> item) -> identity.apply(item.next()),
            item -> item,
            new SmartIterableTemporalDiff<>(
                identity::apply,
                new DbdColumnsOfTable(this.diff.current()),
                new DbdColumnsOfTable(this.diff.next())
            ).intersection()
        );
        final Map<String, Typecast> typecasts = new MapOf<>(
            cast -> cast.current().asString() + cast.next().asString(),
            cast -> cast,
            new TypecastsOfConfigAndDbms(this.config, new PostgresDbms())
        );
        final Func<DbdColumnMapping, Typecast> typecast = col -> {
            final TemporalDiff<DbdColumnMapping> cols = both.get(identity.apply(col));
            final String typecastkey = type.apply(cols.current()) + type.apply(cols.next());
            return new Ternary<>(
                () -> typecasts.containsKey(typecastkey),
                () -> typecasts.get(typecastkey),
                () -> new SimpleTypecast(
                    () -> type.apply(cols.current()),
                    () -> type.apply(cols.next()),
                    false
                )
            ).value();
        };
        return new TextOfMessageFormat(
            "INSERT INTO {0}.{1} ({2})\nSELECT\n{3}\nFROM {0}.{1}{4} AS BEFORE;",
            this.diff.next().signature().name().parent(),
            this.diff.next().signature().name().first(),
            new Joined(", ", new Mapped<>(dbname, next)),
            new JoinedColumnSqls(
                new Mapped<Text>(
                    col -> new TextOfConditionsLines(
                        new MapEntry<>(
                            () -> !both.containsKey(identity.apply(col)),
                            () -> new TextOf(
                                "null -- no matching value from the previous table"
                            )
                        ),
                        new MapEntry<>(
                            () -> both.containsKey(identity.apply(col)),
                            () -> {
                                final TemporalDiff<DbdColumnMapping> cols = both.get(
                                    identity.apply(col)
                                );
                                return new Ternary<Text>(
                                    () -> type.apply(cols.current()).equals(type.apply(cols.next())),
                                    () -> new TextOfMessageFormat(
                                        "BEFORE.{0} -- just copy",
                                        dbname.apply(cols.current())
                                    ),
                                    new Ternary<Text>(
                                        () -> typecast.apply(col).implicit(),
                                        () -> new TextOfMessageFormat(
                                            "BEFORE.{0}::{1} -- implicit cast available",
                                            dbname.apply(cols.current()),
                                            type.apply(cols.next())
                                        ),
                                        () -> new TextOfMessageFormat(
                                            "null -- BEFORE.{0} needs an explicit cast {1}::{2} {3}",
                                            dbname.apply(cols.current()),
                                            type.apply(cols.current()),
                                            type.apply(cols.next()),
                                            new FixMark().asString()
                                        )
                                    )
                                ).value();
                            }
                        )
                    ),
                    next
                )
            ),
            new SwappingEntityNameSuffix()
        ).asString();
    }

    /**
     * The type of that can be constructed of.
     * @since 0.1
     */
    private static class JoinedColumnSqls extends TextEnvelope {

        /**
         * Instantiates a new Joined columns sqls lines.
         * @param lines The lines to join.
         */
        JoinedColumnSqls(final Iterable<Text> lines) {
            super(
                new Joined(
                    () -> "\n",
                    new Mapped<Text>(
                        line -> new Concatenated(
                            () -> "  ",
                            new Ternary<>(
                                () -> line.asString().contains(",")
                                && line.asString().contains("--"),
                                () -> new Replaced(new Replaced(line, ",", ""), " --", ", --"),
                                () -> line
                            ).value()
                        ),
                        new Split(
                            new Joined(() -> ",\n", lines),
                            () -> "\n"
                        )
                    )
                )
            );
        }

    }

}
