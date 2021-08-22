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
package org.fusionsoft.database.dbobject.signature;

import org.cactoos.Text;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.DbObjectType;
import org.fusionsoft.database.DbmsSignature;

public class SimpleDbObjectSignature implements DbObjectSignature {

    private final Text name;

    private final Text parentName;

    private final DbObjectType dbObjectType;

    private final DbmsSignature dbmsSignature;

    public SimpleDbObjectSignature(final Text name, final Text parentName, final DbObjectType dbObjectType, final DbmsSignature dbmsSignature) {
        this.name = name;
        this.parentName = parentName;
        this.dbObjectType = dbObjectType;
        this.dbmsSignature = dbmsSignature;
    }

    @Override
    public final String name() {
        return this.name.toString();
    }

    @Override
    public final String parentName() {
        return this.parentName.toString();
    }

    @Override
    public final DbObjectType type() {
        return this.dbObjectType;
    }

    @Override
    public final DbmsSignature dbmsSignature() {
        return this.dbmsSignature;
    }

}
