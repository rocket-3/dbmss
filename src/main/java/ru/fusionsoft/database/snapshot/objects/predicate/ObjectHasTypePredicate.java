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
import org.cactoos.Func;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The Func of DbObject -> Boolean, that checks {@link DbObject}
 *  has specific {@link ObjectType} node.
 * @param <Y> The type of YamlNode parameter.
 * @since 0.1
 */
public class ObjectHasTypePredicate<Y extends YamlNode> implements Func<DbObject<Y>, Boolean> {

    /**
     * The ObjectType encapsulated.
     */
    private final ObjectType<?> type;

    /**
     * Instantiates a new Object has type predicate.
     * @param type The ObjectType to be encapsulated.
     */
    public ObjectHasTypePredicate(final ObjectType<?> type) {
        this.type = type;
    }

    @Override
    public final Boolean apply(final DbObject<Y> input) {
        return input.signature().type()
            .asString()
            .equals(
                this.type.asString()
            );
    }

}
