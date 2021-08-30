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
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbObjectSignature;
import org.fusionsoft.database.StringProperty;

/**
 * The type Db object of scalar.
 * @see Scalar
 * @see DbObject
 * @since 0.1
 */
public final class DbObjectOfScalar implements DbObject {

    /**
     * The scalar of DbObject to delegate everything on its return value.
     * @see Scalar
     * @see Unchecked
     */
    private final Unchecked<DbObject> scalar;

    /**
     * Instantiates a new DbObject of scalar.
     * @param scalar The function that returns DbObject.
     */
    public DbObjectOfScalar(final Scalar<DbObject> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public DbObjectSignature signature() {
        return this.scalar.value().signature();
    }

    @Override
    public Collection<StringProperty> props() {
        return this.scalar.value().props();
    }

}
