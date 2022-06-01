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
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableEnvelope;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.predicate.InvertedPredicate;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectMentionedInObjectsPredicate;

/**
 * The {@link ObjectsFiltered} by being NOT mentioned
 *  in another {@link Iterable} of {@link DbObject}s.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsNotMentionedInObjects<T extends YamlNode> extends IterableEnvelope<DbObject<T>> {

    /**
     * Instantiates a new Objects not mentioned in.
     * @param blacklist The Objects not to be mentioned in.
     * @param origin The Objects to be filtered.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectsNotMentionedInObjects(
        final Iterable<DbObject<Y>> blacklist,
        final Iterable<DbObject<T>> origin
    ) {
        super(
            new Filtered<>(
                new InvertedPredicate<>(
                    new ObjectMentionedInObjectsPredicate(blacklist)
                ),
                origin
            )
        );
    }

}
