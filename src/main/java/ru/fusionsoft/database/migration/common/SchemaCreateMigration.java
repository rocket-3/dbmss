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

import org.cactoos.Text;
import ru.fusionsoft.database.mapping.dbd.DbdSchemaMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.postgres.PgSchemaCreateSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The Migration to create schema of schema {@link DbObject} and DBMS specified.
 * @since 0.1
 */
public class SchemaCreateMigration implements Migration {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<DbdSchemaMapping> object;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * Instantiates a new Schema create migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public SchemaCreateMigration(
        final DbObject<DbdSchemaMapping> object,
        final Dbms dbms
    ) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Creating schema {0}",
            () -> this.object.signature().name()
        );
    }

    @Override
    public final Text sql() {
        return new TextOfDbmsConditional(
            new PgSchemaCreateSql(this.object),
            () -> "",
            () -> "",
            () -> "",
            this.dbms
        );
    }

}
