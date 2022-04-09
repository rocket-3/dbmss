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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.scalar.Or;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;

public class ObjectsOfOneDependsOnAnother<T extends YamlNode> extends ObjectsOfScalar<T> {

    /**
     * Ctor.
     */
    public ObjectsOfOneDependsOnAnother(
        final Objects<T> one,
        final Objects<?> another
    ) {
        super(
            () -> {
                return new ObjectsFiltered<T>(
                    dbo -> new Or(
                        (DbObject<?> upd) -> new ObjectDependsOnAnotherPredicate(dbo).apply(upd),
                        another
                    ).value(),
                    one
                );
            }
        );
    }

}
