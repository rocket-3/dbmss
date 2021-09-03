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
package org.fusionsoft.database.dbobject;

import java.util.Collection;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.StringProperty;

/**
 * The naive implementation of DbObject.
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
public class SimpleDbObject implements DbObject {

    /**
     * THe signature to return.
     */
    private final DbObjectSignature signature;

    /**
     * The props to return.
     */
    private final Collection<StringProperty> properties;

    /**
     * Instantiates a new Simple db object.
     * @param signature The db object signature to wrap.
     * @param properties The properties to wrap.
     */
    public SimpleDbObject(
        final DbObjectSignature signature,
        final Collection<StringProperty> properties
    ) {
        this.signature = signature;
        this.properties = properties;
    }

    @Override
    public final DbObjectSignature signature() {
        return this.signature;
    }

    @Override
    public final Collection<StringProperty> props() {
        return this.properties;
    }

}