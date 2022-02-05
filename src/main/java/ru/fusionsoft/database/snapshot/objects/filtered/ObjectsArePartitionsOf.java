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
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectHasParentMentionedInPredicate;

/**
 * The {@link ObjectsFiltered} by data tables, which given tables point at by 'parent' node.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectsArePartitionsOf<T extends YamlNode> extends ObjectsFiltered<T> {

    /**
     * Instantiates a new Objects are partitions of.
     * @param all The filtered {@link Objects} to be encapsulated.
     * @param filter The filter {@link Objects} to be encapsulated.
     */
    public ObjectsArePartitionsOf(final Objects<T> all, final Objects<?> filter) {
        super(
            all,
            new ObjectHasParentMentionedInPredicate(filter)
        );
    }

}