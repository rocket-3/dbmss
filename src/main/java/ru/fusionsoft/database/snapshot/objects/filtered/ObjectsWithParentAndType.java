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
package ru.fusionsoft.database.snapshot.objects.filtered;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The Objects filtered by their parent and type given.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsWithParentAndType<T extends YamlNode> extends ObjectsWithType<T> {

    /**
     * Instantiates a new Objects with parent and type.
     * @param parent The {@link DbObject} of parent to be encapsulated.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param objects The {@link Iterable} of {@link DbObject}s to be used.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectsWithParentAndType(
        final DbObject<?> parent,
        final ObjectType<T> type,
        final Iterable<DbObject<Y>> objects
    ) {
        super(
            type,
            new ObjectsWithParent<Y>(
                parent,
                objects
            )
        );
    }

}
