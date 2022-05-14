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
package ru.fusionsoft.database.snapshot.objects.predicate;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The predicate of {@link DbObject}, checks being mentioned in {@link DbObject}'s list.
 * @since 0.1
 */
public class ObjectMentionedInObjectsPredicate extends ObjectMentionedInNamesPredicate {

    /**
     * Instantiates a new Objects with names.
     * @param mentions The objects with names to pass.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectMentionedInObjectsPredicate(
        final Iterable<DbObject<Y>> mentions
    ) {
        super(new Mapped<>(DbObject::signature, mentions));
    }

}
