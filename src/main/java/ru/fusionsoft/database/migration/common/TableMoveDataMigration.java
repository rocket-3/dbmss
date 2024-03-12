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
import org.cactoos.Text;
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.postgres.PgTableMigrateDataOfSwappingEntitySql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The Migration moving and casting data from {@link SwappingEntityNameSuffix}-named brothers
 *  of given tables {@link TemporalDiff}.
 * @since 0.1
 */
public class TableMoveDataMigration implements Migration {

    /**
     * The TemporalDiff encapsulated.
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
     * Instantiates a new Table finish rebuild migration.
     * @param diff The {@link TemporalDiff} of tables {@link DbObject}s to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     */
    public TableMoveDataMigration(
        final TemporalDiff<DbObject<YamlNode>> diff,
        final Dbms dbms,
        final MigrationConfigMapping config
    ) {
        this.diff = diff;
        this.dbms = dbms;
        this.config = config;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Moving data from old table {0}{1} to new version of {0}",
            () -> this.diff.current().signature().name(),
            () -> new SwappingEntityNameSuffix(),
            () -> this.diff.next().signature()
        );
    }

    @Override
    public final Text sql() {
        return new TextOfDbmsConditional(
            new PgTableMigrateDataOfSwappingEntitySql(this.diff, this.config),
            () -> "",
            () -> "",
            () -> "",
            this.dbms
        );
    }

}

