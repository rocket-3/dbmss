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
package org.fusionsoft.database.condition;

import org.fusionsoft.database.Condition;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbmsSignature;

public class DboHasDbmsSignature implements Condition {

    private final DbmsSignature signature;

    private final DbObject dbObject;

    public DboHasDbmsSignature(final DbmsSignature signature, final DbObject dbObject) {
        this.signature = signature;
        this.dbObject = dbObject;
    }

    @Override
    public Boolean value() {
        return dbObject.signature().dbmsSignature().equals(signature);
    }

}
