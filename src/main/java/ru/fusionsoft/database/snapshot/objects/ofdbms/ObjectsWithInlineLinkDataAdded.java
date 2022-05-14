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
package ru.fusionsoft.database.snapshot.objects.ofdbms;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.data.LinkDataObjectsOfTables;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The {@link ObjectsJoined} with {@link LinkDataObjectsOfTables} added.
 * @since 0.1
 */
public class ObjectsWithInlineLinkDataAdded extends ObjectsJoined {

    /**
     * Ctor.
     * @param objects The wrapped objects
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectsWithInlineLinkDataAdded(
        final Iterable<DbObject<Y>> objects
    ) {
        super(
            objects,
            new LinkDataObjectsOfTables(
                new ObjectsWithType<>(
                    new ObjectTypeTable(),
                    objects
                )
            )
        );
    }

}
