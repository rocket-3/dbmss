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
import org.cactoos.scalar.Or;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;

/**
 * The objects, filtered by having sql code origin.
 * @since 0.1
 */
public class ObjectsWithSqlOrigin extends ObjectsFiltered<YamlNode> {

    /**
     * Instantiates a new Objects with sql origin.
     * @param objects The {@link Iterable} {@link DbObject}s to be encapsulated.
     */
    public ObjectsWithSqlOrigin(final Iterable<? extends DbObject<YamlNode>> objects) {
        super(
            ot -> new Or(
                () -> ot.signature().type().equalsTo(new ObjectTypeFunction()),
                () -> ot.signature().type().equalsTo(new ObjectTypeProcedure()),
                () -> ot.signature().type().equalsTo(new ObjectTypeFunction()),
                () -> ot.signature().type().equalsTo(new ObjectTypeTrigger())
            ).value(),
            objects
        );
    }

}
