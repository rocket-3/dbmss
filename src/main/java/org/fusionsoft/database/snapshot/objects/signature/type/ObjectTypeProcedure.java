/*
 * Copyright (C) 2018-2022 FusionSoft
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
package org.fusionsoft.database.snapshot.objects.signature.type;

import org.fusionsoft.database.mapping.dbd.DbdProcedureMapping;

/**
 * The only {@link SimpleObjectType} of {@link DbdProcedureMapping} implementation.
 * @since 0.1
 */
public class ObjectTypeProcedure extends SimpleObjectType<DbdProcedureMapping> {

    /**
     * Instantiates a new Object type procedure.
     */
    public ObjectTypeProcedure() {
        super(x -> new DbdProcedureMapping(x.asMapping()), "procedure");
    }

}
