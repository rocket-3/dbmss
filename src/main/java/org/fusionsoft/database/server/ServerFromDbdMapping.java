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
package org.fusionsoft.database.server;

import java.sql.Connection;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.connection.ConnFromDbdMapping;
import org.fusionsoft.database.dbms.signature.DbmsSignatureFromDbdMapping;
import org.fusionsoft.database.yaml.DBDYamlInput;

public class ServerFromDbdMapping implements Server {

    private final Connection connection;

    private final DbmsSignature dbmsSignature;

    private final CharSequence serverName;

    public ServerFromDbdMapping(final DBDYamlInput dbdYamlInput, final CharSequence serverName) {
        this.connection = new ConnFromDbdMapping(dbdYamlInput, serverName);
        this.dbmsSignature = new DbmsSignatureFromDbdMapping(
            dbdYamlInput,
            serverName
        );
        this.serverName = serverName;
    }

    @Override
    public Connection connection() {
        return this.connection;
    }

    @Override
    public String name() {
        return String.valueOf(serverName);
    }

    @Override
    public DbmsSignature dbmsSignature() {
        return dbmsSignature;
    }

}
