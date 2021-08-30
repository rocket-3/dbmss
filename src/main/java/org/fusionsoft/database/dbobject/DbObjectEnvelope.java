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
 * The DbObject instance wrapping original one and delegating all methods to it.
 * Used to compose list of decorators together by subtyping.
 * @since 0.1
 */
public abstract class DbObjectEnvelope implements DbObject {

    /**
     * The wrapped DbObject instance.
     */
    private final DbObject origin;

    /**
     * Instantiates a new DbObject from other instance.
     * @param origin The wrapped DbObject.
     */
    public DbObjectEnvelope(final DbObject origin) {
        this.origin = origin;
    }

    @Override
    public final DbObjectSignature signature() {
        return this.origin.signature();
    }

    @Override
    public final Collection<StringProperty> props() {
        return this.origin.props();
    }

}
