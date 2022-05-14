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
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectMentionedInNamesPredicate;

/**
 * The objects which are filtered by having the same names in another set of names.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectsMentionedInNames<T extends YamlNode> extends IterableEnvelope<DbObject<T>> {

    /**
     * Instantiates a new Objects mentioned in.
     * @param mentions The {@link ru.fusionsoft.database.snapshot.ObjectSignature}s
     *  to be mentioned in.
     * @param origin The original objects to be filtered.
     */
    public ObjectsMentionedInNames(
        final Iterable<ObjectSignature> mentions,
        final Iterable<DbObject<T>> origin
    ) {
        super(
            new Filtered<DbObject<T>>(
                new ObjectMentionedInNamesPredicate(mentions),
                origin
            )
        );
    }

}
