/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database.migration;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.dbobject.DbObjectsFromDbd;
import org.fusionsoft.database.dbobject.DbObjectsFromServer;
import org.fusionsoft.database.description.dbd.ofyaml.DbdOf;
import org.fusionsoft.database.server.ServerFromDBDYamlInput;
import org.fusionsoft.database.yaml.DBDYamlInput;

/**
 * The type of Migration that can be constructed
 * of DBD yaml input and server name.
 * @since 0.1
 * @checkstyle AbbreviationAsWordInNameCheck (100 lines)
 */
public class DBDToServerMigration implements Migration {

    /**
     * The Migration encapsulated.
     */
    private final Migration migration;

    /**
     * Instantiates a new Dbd to server migration.
     * @param migration The Migration to be encapsulated.
     */
    private DBDToServerMigration(final Migration migration) {
        this.migration = migration;
    }

    /**
     * Instantiates a new Dbd to server migration.
     * @param server The Server to be encapsulated.
     * @param persistent The Collection of DbObject to be encapsulated.
     * @param target The Collection of DbObject to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     * @checkstyle ParameterNumberCheck (48 lines)
     */
    private DBDToServerMigration(
        final Server server,
        final Collection<DbObject> persistent,
        final Collection<DbObject> target,
        final RestoreParams params
    ) {
        this(
            new DboCollectionMigration(
                server,
                persistent,
                target,
                params
            )
        );
    }

    /**
     * Instantiates a new Dbd to server migration.
     * @param input The DBDYamlInput to be encapsulated.
     * @param server The Server to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     */
    private DBDToServerMigration(
        final DBDYamlInput input,
        final Server server,
        final RestoreParams params
    ) {
        this(
            server,
            new DbObjectsFromServer(server),
            new DbObjectsFromDbd(
                new DbdOf(input),
                server.dbmsSignature()
            ),
            params
        );
    }

    /**
     * Instantiates a new Dbd to server migration.
     * @param input The DBDYamlInput to be encapsulated.
     * @param server The CharSequence to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     */
    public DBDToServerMigration(
        final DBDYamlInput input,
        final CharSequence server,
        final RestoreParams params
    ) {
        this(
            input,
            new ServerFromDBDYamlInput(input, server),
            params
        );
    }

    @Override
    public final boolean validate() throws Exception {
        return this.migration.validate();
    }

    @Override
    public final void perform() throws Exception {
        this.migration.perform();
    }

}
