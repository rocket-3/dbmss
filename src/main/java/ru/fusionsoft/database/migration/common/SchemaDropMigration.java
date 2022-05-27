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
import ru.fusionsoft.database.migration.postgres.PgSchemaDropSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

public class SchemaDropMigration implements Migration {

    private final DbObject<DbdSchemaMapping> object;

    private final Dbms dbms;

    public SchemaDropMigration(
        final DbObject<DbdSchemaMapping> object,
        final Dbms dbms
    ) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public Text description() {
        return new TextOfMessageFormat(
            "Dropping schema {0}",
            () -> this.object.signature().name()
        );
    }

    @Override
    public Text sql() {
        return new TextOfDbmsConditional(
            new PgSchemaDropSql(this.object),
            () -> "",
            () -> "",
            () -> "",
            dbms
        );
    }

}