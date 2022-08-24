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
package ru.fusionsoft.database.migration.scenarios;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.common.TableMergeMigrations;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.ObjectsOfObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.sorted.ObjectsSortedByDependencies;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Migrations to merge tables into new state, using DML or swapping table ways, if needed,
 *  see {@link TableMergeMigrations}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class TablesMergeOrPrepareToRebuildMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Instantiates a new Tables merge or prepare to rebuild migrations.
     * @param diff The {@link TemporalDiff} of Iterable of DbObject to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TablesMergeOrPrepareToRebuildMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this(new ObjectsDiff(diff), dbms, config);
    }

    /**
     * Instantiates a new Tables merge or prepare to rebuild migrations.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TablesMergeOrPrepareToRebuildMigrations(
        final ObjectsDiff diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        super(
            () -> {
                return new Joined<Migration>(
                    new Mapped<>(
                        entry -> {
                            return new TableMergeMigrations(entry, dbms, config);
                        },
                        new ObjectsOfObjectsDiff(
                            TemporalDiff::current,
                            objects -> new ObjectsSortedByDependencies(
                                new ObjectsWithType(
                                    new ObjectTypeTable(),
                                    objects
                                )
                            ),
                            diff.changed()
                        )
                    )
                );
            }
        );
    }

}
