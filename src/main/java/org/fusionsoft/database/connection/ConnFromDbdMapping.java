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
package org.fusionsoft.database.connection;

import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.connection.ConnectionEnvelope;
import org.fusionsoft.lib.connection.ConnectionOfScalar;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The Connection obtained from DBD's 'server' section data.
 * @see java.sql.Connection
 * @since 0.1
 */
public class ConnFromDbdMapping extends ConnectionEnvelope {

    /**
     * Instantiates a new Conn from dbd mapping.
     * @param input The DBD yaml input.
     * @param server The name to take from 'server' section of DBD.
     */
    public ConnFromDbdMapping(
        final DBDYamlInput input,
        final CharSequence server
    ) {
        super(
            new ConnectionOfScalar(
                () -> {
                    input.notifyAll();
                    server.notifyAll();
                    throw new NotImplemented();
                }
            )
        );
    }

}
