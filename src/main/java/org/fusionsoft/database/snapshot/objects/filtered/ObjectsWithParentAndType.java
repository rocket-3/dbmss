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
package org.fusionsoft.database.snapshot.objects.filtered;

import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;

/**
 * The Objects filtered by their parent and type given.
 * @since 0.1
 */
public class ObjectsWithParentAndType extends ObjectsWithParent {

    /**
     * Instantiates a new Objects with parent and type.
     * @param parent The DbObject<?> to be encapsulated.
     * @param type The ObjectType to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public ObjectsWithParentAndType(
        final DbObject<?> parent,
        final ObjectType type,
        final Objects objects
    ) {
        super(parent, new ObjectsWithType(type, objects));
    }

}
