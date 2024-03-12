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
package ru.fusionsoft.database.migration.condition;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Scalar;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Not;
import org.cactoos.scalar.Or;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.migration.diff.SmartIterableTemporalDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.order.OrdersOfColumnsEquality;
import ru.fusionsoft.database.migration.order.OrdersOfColumnsMergedLikeDbms;
import ru.fusionsoft.database.migration.order.OrdersOfColumnsOfTable;
import ru.fusionsoft.database.migration.typecasts.Typecast;
import ru.fusionsoft.database.migration.typecasts.TypecastsOfConfigAndTableDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectFieldMaybeEmpty;
import ru.fusionsoft.database.text.DbdColumnIdentity;

/**
 * The predicate {@link Scalar} checks given current table {@link DbObject} of {@link TemporalDiff}
 *  cannot be straight altered to next table state.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines).
 */
public class TableNeedsRebuild implements Scalar<Boolean> {

    /**
     * The {@link TemporalDiff} of {@link DbObject} encapsulated.
     */
    private final TemporalDiff<DbObject<YamlNode>> diff;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * The MigrationConfigMapping encapsulated.
     */
    private final MigrationConfigMapping config;

    /**
     * Instantiates a new Table needs rebuild.
     * @param diff The {@link TemporalDiff} of {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TableNeedsRebuild(
        final TemporalDiff<DbObject<YamlNode>> diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this.diff = diff;
        this.dbms = dbms;
        this.config = config;
    }

    @Override
    public final Boolean value() {
        final SmartIterableTemporalDiff<DbdColumnMapping> columns = new SmartIterableTemporalDiff<>(
            DbdColumnIdentity::new,
            new DbdColumnsOfTable(this.diff.current()),
            new DbdColumnsOfTable(this.diff.next())
        );
        return new Unchecked<>(
            new Or(
                new Not(
                    new OrdersOfColumnsEquality(
                        new OrdersOfColumnsOfTable(this.diff.next()),
                        new OrdersOfColumnsMergedLikeDbms(
                            new DbdColumnsOfTable(this.diff.current()),
                            columns.deleted(),
                            columns.added()
                        )
                    )
                ),
                new Not(
                    new And(
                        Typecast::implicit,
                        new TypecastsOfConfigAndTableDiff(this.config, this.diff, this.dbms)
                    )
                ),
                new Not(
                    () -> new TextOfObjectFieldMaybeEmpty(
                        this.diff.current(),
                        DbdTableFields.PARTKEYDEFINITION
                    ).asString().equals(
                        new TextOfObjectFieldMaybeEmpty(
                            this.diff.next(),
                            DbdTableFields.PARTKEYDEFINITION
                        ).asString()
                    )
                ),
                new Not(
                    () -> new TextOfObjectFieldMaybeEmpty(
                        this.diff.current(),
                        DbdTableFields.PARTKEYRANGE
                    ).asString().equals(
                        new TextOfObjectFieldMaybeEmpty(
                            this.diff.next(),
                            DbdTableFields.PARTKEYRANGE
                        ).asString()
                    )
                )
            )
        ).value();
    }

}
