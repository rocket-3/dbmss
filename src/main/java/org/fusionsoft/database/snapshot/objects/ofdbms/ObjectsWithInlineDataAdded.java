/*
 * Copyright (C) 2018-2021 FusionSoft
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
import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.data.InlineDataObjectOfTable;
import org.fusionsoft.database.snapshot.objects.DefaultObjectsJoined;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

public class ObjectsWithInlineDataAdded extends DefaultObjectsJoined {

    /**
     * Ctor.
     * @param iterable The wrapped iterable
     */
    public ObjectsWithInlineDataAdded(
        final Iterable<? extends DbObject<?>> iterable,
        final Connection connection
    ) {
        super(
            iterable,
            new Mapped<>(
                x -> new InlineDataObjectOfTable(x, connection),
                new ObjectsWithType<>(
                    new ObjectTypeTable(),
                    new ObjectsFromServer(connection)
                )
            )
        );
    }

}
