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
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sorted;
import org.cactoos.map.MapEntry;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.migration.diff.SmartIterableTemporalDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldDifference;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfText;
import ru.fusionsoft.database.text.DbdColumnIdentity;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.database.text.TextOfConditionsLines;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to alter current table to next table state.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines).
 * @checkstyle LineLengthCheck (200 lines).
 */
@SuppressWarnings("PMD")
public class PgTableAlterSql implements Text {

    /**
     * The TemporalDiff of table {@link DbObject}s.
     */
    private final TemporalDiff<DbObject<YamlNode>> object;

    /**
     * Instantiates a new Pg table alter sql.
     * @param object The TemporalDiff of table {@link DbObject}s.
     */
    public PgTableAlterSql(final TemporalDiff<DbObject<YamlNode>> object) {
        this.object = object;
    }

    @Override
    public final String asString() {
        final SmartIterableTemporalDiff<DbdColumnMapping> cols = new SmartIterableTemporalDiff<>(
            DbdColumnIdentity::new,
            new DbdColumnsOfTable(this.object.current()),
            new DbdColumnsOfTable(this.object.next())
        );
        final Iterable<TemporalDiff<DbdColumnMapping>> changed = cols.changedBy(
            col -> new TextOfObjectField(
                col::asMapping,
                DbdColumnFields.DBMSTYPE
            ).asString().hashCode()
        );
        return new TextOfConditionsLines(
            new Joined<Map.Entry<Scalar<Boolean>, Scalar<Text>>>(
                new Mapped<Map.Entry<Scalar<Boolean>, Scalar<Text>>>(
                    col -> new MapEntry<>(
                        () -> !new SetOf<>(cols.deleted()).isEmpty(),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} DROP COLUMN {2};",
                            () -> this.object.next().signature().name().parent(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(col::asMapping, DbdColumnFields.DBNAME)
                        )
                    ),
                    cols.deleted()
                ),
                new Mapped<Map.Entry<Scalar<Boolean>, Scalar<Text>>>(
                    col -> new MapEntry<>(
                        () -> !new SetOf<>(cols.added()).isEmpty(),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} ADD COLUMN {2} {3};",
                            () -> this.object.next().signature().name().parent(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(col::asMapping, DbdColumnFields.DBNAME),
                            () -> new TextOfObjectField(col::asMapping, DbdColumnFields.DBMSTYPE)
                        )
                    ),
                    new Sorted<>(
                        Comparator.comparing(
                            col -> Integer.valueOf(
                                new TextOfObjectField(
                                    col::asMapping,
                                    DbdColumnFields.ORDER
                                ).asString()
                            )
                        ),
                        cols.added()
                    )
                ),
                new Mapped<Map.Entry<Scalar<Boolean>, Scalar<Text>>>(
                    col -> new MapEntry<>(
                        () -> !new SetOf<>(changed).isEmpty(),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} ALTER COLUMN {2} TYPE {3} USING ({2}::{3});",
                            () -> this.object.next().signature().name().parent(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(
                                col.next()::asMapping,
                                DbdColumnFields.DBNAME
                            ),
                            () -> new TextOfObjectField(
                                col.next()::asMapping,
                                DbdColumnFields.DBMSTYPE
                            )
                        )
                    ),
                    changed
                ),
                new IterableOf<>(
                    new MapEntry<>(
                        new ObjectFieldDifference(this.object, DbdTableFields.TABLESPACE),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} SET TABLESPACE \"{2}\";",
                            () -> this.object.next().signature().name().parent(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(
                                this.object.next(),
                                DbdTableFields.TABLESPACE
                            )
                        )
                    ),
                    new MapEntry<>(
                        new ObjectFieldDifference(this.object, DbdTableFields.TABLESPACE),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} SET TABLESPACE \"{2}\";",
                            () -> this.object.next().signature().name().parent(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(
                                this.object.next(),
                                DbdTableFields.TABLESPACE
                            )
                        )
                    ),
                    new MapEntry<>(
                        new ObjectFieldDifference(this.object, DbdTableFields.OWNER),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} OWNER TO \"{2}\";",
                            () -> this.object.next().signature().name().parent(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(
                                this.object.next(),
                                DbdTableFields.OWNER
                            )
                        )
                    ),
                    new MapEntry<>(
                        new ObjectFieldDifference(this.object, DbdTableFields.PARENT),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1}{2} DETACH PARTITION {0}.{3};",
                            () -> this.object.current().signature().name().parent(),
                            () -> new SimpleObjectNameOfText(
                                new TextOfObjectField(
                                    this.object.current(),
                                    DbdTableFields.PARENT
                                )
                            ).first(),
                            () -> new SwappingEntityNameSuffix(),
                            () -> this.object.current().signature().name().first()
                        )
                    ),
                    new MapEntry<>(
                        new ObjectFieldDifference(this.object, DbdTableFields.PARENT),
                        () -> new TextOfMessageFormat(
                            "ALTER TABLE {0}.{1} ATTACH PARTITION {0}.{2} {3};",
                            () -> this.object.current().signature().name().parent(),
                            () -> new SimpleObjectNameOfText(
                                new TextOfObjectField(
                                    this.object.current(),
                                    DbdTableFields.PARENT
                                )
                            ).first(),
                            () -> this.object.next().signature().name().first(),
                            () -> new TextOfObjectField(
                                this.object.next(),
                                DbdTableFields.PARTKEYRANGE
                            )
                        )
                    )
                )
            )
        ).asString();
    }

}
