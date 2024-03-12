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
package ru.fusionsoft.database.migration.common;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.condition.TableNeedsRebuild;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * The Migration of tables {@link TemporalDiff}, deciding to do {@link TableAlterMigration},
 *  or, if not possible, {@link AnyObjectRenameMigration} + {@link TableCreateMigration}
 *  (which needs {@link TableMoveDataMigration} later).
 * @since 0.1
 */
public class TableMergeMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Instantiates a new Table merge migrations.
     * @param diff The {@link TemporalDiff} of tables {@link DbObject}s to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TableMergeMigrations(
        final TemporalDiff<DbObject<YamlNode>> diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        super(
            () -> {
                return new Ternary<Iterable<Migration>>(
                    new TableNeedsRebuild(diff, dbms, config),
                    new IterableOf<>(
                        new AnyObjectRenameMigration(diff.current(), dbms),
                        new TableCreateMigration(diff.next(), dbms)
                    ),
                    new IterableOf<>(
                        new TableAlterMigration(diff, dbms)
                    )
                ).value();
            }
        );
    }

}
