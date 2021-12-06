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

import com.amihaiemil.eoyaml.YamlNode;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsCasted;
import org.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import org.fusionsoft.database.snapshot.objects.predicate.ObjectHasTypePredicate;
import org.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The {@link Objects} filtered by {@link ObjectType}.
 * @since 0.1
 */
public class ObjectsWithType<M extends YamlNode> extends ObjectsCasted<M> {

    /**
     * Instantiates a new Objects with type.
     * @param type The ObjectType to be encapsulated.
     * @param origin The Objects to be encapsulated.
     */
    public ObjectsWithType(
        final ObjectType<M> type,
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
