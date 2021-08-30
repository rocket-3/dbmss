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

/**
 * The type of Server that can be constructed of DBD Yaml input.
 * @since 0.1
 * @checkstyle AbbreviationAsWordInNameCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public class ServerFromDBDYamlInput implements Server {

    /**
     * The Connection encapsulated.
     */
    private final Connection connection;

    /**
     * The DbmsSignature encapsulated.
     */
    private final DbmsSignature signature;

    /**
     * The CharSequence encapsulated.
     */
    private final CharSequence name;

    /**
     * Instantiates a new Server from dbd yaml mapping.
     * @param input The DBDYamlInput to be encapsulated.
     * @param server The CharSequence to be encapsulated.
     */
    public ServerFromDBDYamlInput(final DBDYamlInput input, final CharSequence server) {
        this.connection = new ConnFromDbdMapping(input, server);
        this.signature = new DbmsSignatureFromDbdMapping(
            input,
            server
        );
        this.name = server;
    }

    @Override
    public final Connection connection() {
        return this.connection;
    }

    @Override
    public final String name() {
        return String.valueOf(this.name);
    }

    @Override
    public final DbmsSignature dbmsSignature() {
        return this.signature;
    }

}
