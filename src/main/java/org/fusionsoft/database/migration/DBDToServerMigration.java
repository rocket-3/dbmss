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
 *
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
import org.fusionsoft.database.server.ServerFromDbdMapping;
import org.fusionsoft.database.yaml.DBDYamlInput;

public class DBDToServerMigration implements Migration {

    private final Migration migration;

    private DBDToServerMigration(final Migration migration) {
        this.migration = migration;
    }

    private DBDToServerMigration(final Server server, final Collection<DbObject> persistentDBOs, final Collection<DbObject> targetDBOs, final RestoreParams restoreParams) {
        this(
            new DboCollectionMigration(
                server,
                persistentDBOs,
                targetDBOs,
                restoreParams
            )
        );
    }

    //there we can insert our stick on dbd by server validation
    /*public*/
    private DBDToServerMigration(final DBDYamlInput dbdYamlInput, final Server server, final RestoreParams restoreParams) {
        this(
            server,
            new DbObjectsFromServer(server),
            new DbObjectsFromDbd(
                new DbdOf(dbdYamlInput),
                server.dbmsSignature()
            ),
            restoreParams
        );
    }

    public DBDToServerMigration(final DBDYamlInput dbdYamlInput, final CharSequence serverName, final RestoreParams restoreParams) {
        this(
            dbdYamlInput,
            new ServerFromDbdMapping(dbdYamlInput, serverName),
            restoreParams
        );
    }

    //    public DBDToServerMigration(YamlDBDMapping yamlDbdMapping, CharSequence serverName, RestoreParams restoreParams) {
    //        this.server = new ServerFromDbdMapping(yamlDbdMapping, serverName);
    //        this.migration = new DboCollectionMigration(
    //            server, 
    //            new DbObjectsFromServer(server),
    //            new DbObjectsFromDbdMapping(
    //                new YamlIUDBD(yamlDbdMapping), 
    //                server.dbmsSignature()
    //            ),
    //            restoreParams
    //        );
    //    }
    @Override
    public boolean validate() throws Exception {
        return this.migration.validate();
    }

    @Override
    public void perform() throws Exception {
        this.migration.perform();
    }

}
