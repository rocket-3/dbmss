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
import ru.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.postgres.PgIndexCreateSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The Migration to create index of index {@link DbObject} and DBMS specified.
 * @since 0.1
 */
public class IndexCreateMigration implements Migration {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<DbdIndexMapping> object;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * Instantiates a new Index create migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public IndexCreateMigration(final DbObject<DbdIndexMapping> object, final Dbms dbms) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Creating index {0} of table {1}",
            () -> this.object.signature().name().parent(),
            () -> this.object.signature().name().first()
        );
    }

    @Override
    public final Text sql() {
        return new TextOfDbmsConditional(
            new PgIndexCreateSql(this.object),
            () -> "",
            () -> "",
            () -> "",
            this.dbms
        );
    }

}
