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
package ru.fusionsoft.database.migration.scenarios.main;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sticky;
import org.cactoos.text.Newline;
import org.cactoos.text.Repeated;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.MigrationJoined;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.scenarios.ConstraintsAndIndexesCreateNewMigrations;
import ru.fusionsoft.database.migration.scenarios.ConstraintsAndIndexesDropMigrations;
import ru.fusionsoft.database.migration.scenarios.SchemasCreateNewMigrations;
import ru.fusionsoft.database.migration.scenarios.SequencesCreateNewMigrations;
import ru.fusionsoft.database.migration.scenarios.SqlsCreateMigrations;
import ru.fusionsoft.database.migration.scenarios.SqlsDropCurrentMigrations;
import ru.fusionsoft.database.migration.scenarios.TablesCreateNewMigrations;
import ru.fusionsoft.database.migration.scenarios.TablesDropDeletedMigrations;
import ru.fusionsoft.database.migration.scenarios.TablesFinishRebuildMigrations;
import ru.fusionsoft.database.migration.scenarios.TablesMergeOrPrepareToRebuildMigrations;
import ru.fusionsoft.database.migration.scenarios.UdtsCreateNewMigrations;
import ru.fusionsoft.database.migration.scenarios.UdtsPrepareToMergeMigrations;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;

/**
 * Sql migration, constructed of {@link TemporalDiff} of Iterable of {@link DbObject},
 *  it also needs migration config data, which contains typecasts config and other things.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class MigrationOfObjectsDiff implements Migration {

    /**
     * The Migrations encapsulated.
     */
    private final Iterable<Migration> migrations;

    /**
     * Instantiates a new Migration of objects diff.
     * @param migrations The Iterable {@link Migration} to be encapsulated.
     */
    public MigrationOfObjectsDiff(final Iterable<Migration> migrations) {
        this.migrations = migrations;
    }

    /**
     * Instantiates a new Migration of objects diff.
     * @param diff The {@link TemporalDiff} of Iterable of {@link DbObject}.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public MigrationOfObjectsDiff(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this(
            new ObjectsDiff(diff),
            dbms,
            config
        );
    }

    /**
     * Instantiates a new Migration of objects diff.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public MigrationOfObjectsDiff(
        final ObjectsDiff diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this(
            new Sticky<Migration>(
                new MigrationJoined(
                    "Drop constraints to make tables free",
                    new ConstraintsAndIndexesDropMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Drop code objects to make tables free",
                    new SqlsDropCurrentMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Create new schemas",
                    new SchemasCreateNewMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Create new sequences",
                    new SequencesCreateNewMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Preparing to merge UDTs",
                    new UdtsPrepareToMergeMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Creating new UDTs",
                    new UdtsCreateNewMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Altering or preparing to rebuild tables",
                    new TablesMergeOrPrepareToRebuildMigrations(diff, dbms, config)
                ),
                new MigrationJoined(
                    "New tables creation",
                    new TablesCreateNewMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Rebuilding tables",
                    new TablesFinishRebuildMigrations(diff, dbms, config)
                ),
                new MigrationJoined(
                    "Drop not existant tables",
                    new TablesDropDeletedMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Create new code objects",
                    new SqlsCreateMigrations(diff, dbms)
                ),
                new MigrationJoined(
                    "Create new constraints",
                    new ConstraintsAndIndexesCreateNewMigrations(diff, dbms)
                )
            )
        );
    }

    @Override
    public final Text description() {
        return new org.cactoos.text.Joined(
            new Newline(),
            new Mapped<Text>(
                Migration::description,
                this.migrations
            )
        );
    }

    @Override
    public final Text sql() {
        return new org.cactoos.text.Joined(
            new Repeated(new Newline(), 2),
            new Filtered<Text>(
                text -> text.asString().length() > 0,
                new Mapped<Text>(
                    Migration::sql,
                    this.migrations
                )
            )
        );
    }

}
