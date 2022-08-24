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
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.postgres.PgTableAlterSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The Migration to alter current table to next table state of {@link TemporalDiff}
 *  and {@link Dbms} specified.
 * @since 0.1
 */
public class TableAlterMigration implements Migration {

    /**
     * The {@link TemporalDiff} of {@link DbObject} encapsulated.
     */
    private final TemporalDiff<DbObject<YamlNode>> object;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * Instantiates a new Table alter migration.
     * @param object The {@link TemporalDiff} of tables to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public TableAlterMigration(
        final TemporalDiff<DbObject<YamlNode>> object,
        final Dbms dbms
    ) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Altering table {0}",
            () -> this.object.current().signature().name()
        );
    }

    @Override
    public final Text sql() {
        return new TextOfDbmsConditional(
            new PgTableAlterSql(this.object),
            () -> "",
            () -> "",
            () -> "",
            this.dbms
        );
    }

}
