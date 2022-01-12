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
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsCasted;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectHasTypePredicate;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The {@link Objects} filtered by {@link ObjectType}.
 * @param <T> The type of {@link ObjectType} parameter.
 * @since 0.1
 */
public class ObjectsWithType<T extends YamlNode> extends ObjectsCasted<T> {

    /**
     * Instantiates a new Objects with type.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param origin The {@link Objects} to be encapsulated.
     */
    public ObjectsWithType(
        final ObjectType<T> type,
        final Objects<?> origin
    ) {
        super(
            type::node,
            new ObjectsFiltered<>(
                new ObjectHasTypePredicate(type),
                origin
            )
        );
    }

}
