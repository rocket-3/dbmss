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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ConnFromDbdMapping extends ConnectionEnvelope {

    private final DBDYamlInput dbdYamlInput;

    private final CharSequence serverName;

    public ConnFromDbdMapping(final DBDYamlInput dbdYamlInput, final CharSequence serverName) {
        super(new ConnectionOfScalar(() -> {
            throw new NotImplementedException();
        }));
        this.dbdYamlInput = dbdYamlInput;
        this.serverName = serverName;
    }

}
