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
import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.predicate.JoinedAndPredicate;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectHasNameOfRegexpPredicate;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectHasTypeOfRegexpPredicate;

/**
 * The {@link ObjectsFiltered} by name and type matching regexp separately.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsWithTypeAndNameMatchesRegexp<T extends YamlNode> extends ObjectsFiltered<T> {

    /**
     * Instantiates a new Objects with type and name matches regexp.
     * @param type The regexp pattern of object type.
     * @param name The regexp pattern of object name.
     * @param origin The {@link Object} to be encapsulated.
     */
    public ObjectsWithTypeAndNameMatchesRegexp(
        final Text type,
        final Text name,
        final Iterable<DbObject<T>> origin
    ) {
        super(
            origin,
            new JoinedAndPredicate<>(
                new ObjectHasTypeOfRegexpPredicate(type),
                new ObjectHasNameOfRegexpPredicate(name)
            )
        );
    }

}
