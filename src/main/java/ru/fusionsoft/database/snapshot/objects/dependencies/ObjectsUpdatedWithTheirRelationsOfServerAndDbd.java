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
import org.cactoos.iterable.Joined;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsMentionedIn;

public class ObjectsUpdatedWithTheirRelationsOfServerAndDbd<T extends YamlNode> extends ObjectsOfScalar<T> {

    public ObjectsUpdatedWithTheirRelationsOfServerAndDbd(
        final Objects<T> update,
        final Objects<T> server,
        final Objects<?> dbd
    ) {
        super(
            () -> {
                final Objects<T> ofserver = new ObjectsOfOneDependsOnAnotherRecursive<>(
                    server,
                    update
                );
                return new Joined<DbObject<T>>(
                    update,
                    ofserver,
                    new ObjectsMentionedIn<T>(
                        new ObjectsOfOneDependsOnAnotherRecursive<>(
                            dbd,
                            ofserver
                        ),
                        server
                    )
                );
            }
        );
    }

}
