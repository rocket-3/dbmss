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
package ru.fusionsoft.database.snapshot.objects.signature.type;

import ru.fusionsoft.database.mapping.dbd.DbdTupleMapping;

/**
 * The only {@link SimpleObjectType} of {@link DbdTupleMapping} implementation.
 * @since 0.1
 */
public class ObjectTypeTuple extends SimpleObjectType<DbdTupleMapping> {

    /**
     * Instantiates a new Object type tuple.
     */
    public ObjectTypeTuple() {
        super(x -> new DbdTupleMapping(x.asMapping()), "tuple");
    }

}
