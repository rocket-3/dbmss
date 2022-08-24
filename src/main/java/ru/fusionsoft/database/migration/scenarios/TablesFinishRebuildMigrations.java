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
import org.cactoos.Func;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.common.TableDropSwappingEntityMigration;
import ru.fusionsoft.database.migration.common.TableMoveDataMigration;
import ru.fusionsoft.database.migration.condition.TableNeedsRebuild;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldExistence;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Migrations to finish migration of tables, modified by using swapping tables, including move data
 *  and delete swapping table steps.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class TablesFinishRebuildMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Instantiates a new Tables finish rebuild migrations.
     * @param diff The {@link TemporalDiff} of Iterable of DbObject to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TablesFinishRebuildMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this(new ObjectsDiff(diff), dbms, config);
    }

    /**
     * Instantiates a new Tables finish rebuild migrations.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TablesFinishRebuildMigrations(
        final ObjectsDiff diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        super(
            () -> {
                final Func<TemporalDiff<DbObject<YamlNode>>, Boolean> predicate = x -> new And(
                    () -> x.current().signature().type().equalsTo(
                        new ObjectTypeTable()
                    ),
                    () -> !new ObjectFieldExistence(
                        x.next(),
                        DbdTableFields.PARENT
                    ).value(),
                    () -> new TableNeedsRebuild(x, dbms, config).value()
                ).value();
                final Filtered<TemporalDiff<DbObject<YamlNode>>> filtered = new Filtered<>(
                    predicate,
                    diff.changed()
                );
                return new Joined<Migration>(
                    new Mapped<Migration>(
                        entry -> new TableMoveDataMigration(entry, dbms, config),
                        filtered
                    ),
                    new Mapped<Migration>(
                        entry -> new TableDropSwappingEntityMigration(entry.current(), dbms),
                        filtered
                    )
                );
            }
        );
    }

}
