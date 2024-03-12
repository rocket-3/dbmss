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
package ru.fusionsoft.database.snapshot.objects.generated;

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.data.InlineRowsDataObjectsOfConnection;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The {@link ObjectsJoined} with {@link InlineRowsDataObjectsOfConnection} added.
 * @since 0.1
 */
public class ObjectsWithInlineRowsDataAdded extends ObjectsJoined {

    /**
     * Ctor.
     * @param objects The wrapped objects
     * @param connection The {@link Connection} to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectsWithInlineRowsDataAdded(
        final Iterable<DbObject<Y>> objects,
        final Connection connection
    ) {
        super(
            objects,
            new InlineRowsDataObjectsOfConnection(
                connection,
                new ObjectsWithTypeCasted<>(
                    new ObjectTypeTable(),
                    objects
                )
            )
        );
    }

    /**
     * Instantiates a new Objects with inline rows data added.
     * @param connection The {@link Connection} to be encapsulated.
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectsWithInlineRowsDataAdded(
        final Connection connection,
        final Iterable<DbObject<Y>> objects
    ) {
        this(objects, connection);
    }

}
