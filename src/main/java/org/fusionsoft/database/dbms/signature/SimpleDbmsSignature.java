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

public class SimpleDbmsSignature implements DbmsSignature {

    private final DbmsName dbmsName;

    private final DbmsVersion dbmsVersion;

    public SimpleDbmsSignature(final DbmsName dbmsName, final DbmsVersion dbmsVersion) {
        this.dbmsName = dbmsName;
        this.dbmsVersion = dbmsVersion;
    }

    @Override
    public DbmsName dbmsName() {
        return this.dbmsName;
    }

    @Override
    public String dbmsVersion() {
        return this.dbmsVersion.toString();
    }

}
