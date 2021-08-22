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
package org.fusionsoft.database.dbms.signature;

import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.connection.ConnFromDbdMapping;
import org.fusionsoft.database.yaml.DBDYamlInput;

public class DbmsSignatureFromDbdMapping implements DbmsSignature {

    private final DbmsSignature dbmsSignature;

    public DbmsSignatureFromDbdMapping(final DBDYamlInput dbdYamlInput, final CharSequence serverName) {
        this.dbmsSignature = new DbmsSignatureFromConnection(
            new ConnFromDbdMapping(
                dbdYamlInput,
                serverName
            )
        );
    }

    @Override
    public DbmsName dbmsName() {
        return dbmsSignature.dbmsName();
    }

    @Override
    public String dbmsVersion() {
        return dbmsSignature.dbmsVersion();
    }

}
