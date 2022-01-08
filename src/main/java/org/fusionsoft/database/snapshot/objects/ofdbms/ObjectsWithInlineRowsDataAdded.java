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
package org.fusionsoft.database.snapshot.objects.ofdbms;

import java.sql.Connection;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.data.RowsDataObjectsOfConnection;
import org.fusionsoft.database.snapshot.objects.DefaultObjectsJoined;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The {@link DefaultObjectsJoined} with {@link RowsDataObjectsOfConnection} added.
 * @since 0.1
 */
public class ObjectsWithInlineRowsDataAdded extends DefaultObjectsJoined {

    /**
     * Ctor.
     * @param objects The wrapped objects
     * @param connection The {@link Connection} to be encapsulated.
     */
    public ObjectsWithInlineRowsDataAdded(
        final Objects<?> objects,
        final Connection connection
    ) {
        super(
            objects,
            new RowsDataObjectsOfConnection(
                connection,
                new ObjectsWithType<>(
                    new ObjectTypeTable(),
                    objects
                )
            )
        );
    }

    /**
     * Instantiates a new Objects with inline rows data added.
     * @param connection The {@link Connection} to be encapsulated.
     * @param objects The {@link Objects} to be encapsulated.
     */
    public ObjectsWithInlineRowsDataAdded(
        final Connection connection,
        final Objects<?> objects
    ) {
        this(objects, connection);
    }

}