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

/**
 * The naive impl. of DbObjectSignature.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class SimpleDbObjectSignature implements DbObjectSignature {

    /**
     * The name encapsulated.
     */
    private final Text name;

    /**
     * The parent name encapsulated.
     */
    private final Text parent;

    /**
     * The DbObjectType encapsulated.
     */
    private final DbObjectType type;

    /**
     * The DbmsSignature encapsulated.
     */
    private final DbmsSignature dbms;

    /**
     * Instantiates a new Simple db object signature.
     * @param name The name of object.
     * @param parent The name of hierarchical upper object or empty text.
     * @param type The type of object.
     * @param dbms The signature of DBMS it belongs.
     * @checkstyle ParameterNumberCheck (4 lines)
     */
    public SimpleDbObjectSignature(
        final Text name,
        final Text parent,
        final DbObjectType type,
        final DbmsSignature dbms
    ) {
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.dbms = dbms;
    }

    @Override
    public final String name() {
        return this.name.toString();
    }

    @Override
    public final String parentName() {
        return this.parent.toString();
    }

    @Override
    public final DbObjectType type() {
        return this.type;
    }

    @Override
    public final DbmsSignature dbmsSignature() {
        return this.dbms;
    }

}
