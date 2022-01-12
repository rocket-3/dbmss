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
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectMentionedInPredicate;

/**
 * The {@link ObjectsFiltered} by being mentioned in another {@link Objects}.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsMentionedIn<T extends YamlNode> extends ObjectsFiltered<T> {

    /**
     * Instantiates a new Objects mentioned in.
     * @param mentions The Objects to be mentioned in.
     * @param origin The Objects to be filtered.
     */
    public ObjectsMentionedIn(final Objects<?> mentions, final Objects<T> origin) {
        super(origin, new ObjectMentionedInPredicate(mentions));
    }

}
