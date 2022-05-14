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
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The predicate, testing object has 'parent' node pointing at some of given objects.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class TargetObjectParentIsOneOfObjectsPredicate
    extends TargetObjectParentIsOneOfNamesPredicate {

    /**
     * Instantiates a new Object has parent mentioned in predicate.
     * @param mentions The objects with names to pass.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> TargetObjectParentIsOneOfObjectsPredicate(
        final Iterable<? extends DbObject<Y>> mentions
    ) {
        super(new Mapped<>(DbObject::signature, mentions));
    }

    /**
     * Instantiates a new Object has parent mentioned in predicate.
     * @param mentions The objects with names to pass.
     * @param <Y> The type of YamlNode parameter.
     */
    @SafeVarargs
    public <Y extends YamlNode> TargetObjectParentIsOneOfObjectsPredicate(
        final DbObject<Y>... mentions
    ) {
        this(
            new IterableOf<>(mentions)
        );
    }

}
