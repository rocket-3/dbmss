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
package org.fusionsoft.database.dbms.signature;

import org.fusionsoft.database.DbmsName;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.DbmsVersion;
import org.fusionsoft.database.connection.ConnFromDbdMapping;
import org.fusionsoft.database.yaml.DBDYamlInput;

/**
 * The type Dbms signature from dbd mapping.
 * @since 0.1
 */
public class DbmsSignatureFromDbdMapping implements DbmsSignature {

    /**
     * The delegate of DbmsSignature.
     */
    private final DbmsSignature delegate;

    /**
     * Instantiates a new Dbms signature from dbd mapping.
     * @param input The DBDYamlInput to be encapsulated.
     * @param server The CharSequence to be encapsulated.
     */
    public DbmsSignatureFromDbdMapping(
        final DBDYamlInput input,
        final CharSequence server
    ) {
        this.delegate = new DbmsSignatureFromConnection(
            new ConnFromDbdMapping(
                input,
                server
            )
        );
    }

    @Override
    public final DbmsName dbmsName() {
        return this.delegate.dbmsName();
    }

    @Override
    public final DbmsVersion dbmsVersion() {
        return this.delegate.dbmsVersion();
    }

}
