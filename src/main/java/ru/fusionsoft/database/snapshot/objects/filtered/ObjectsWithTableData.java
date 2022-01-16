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

import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsFiltered;
import ru.fusionsoft.database.snapshot.objects.predicate.ObjectHasDataNodePredicate;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The {@link ObjectsFiltered} that has {@link ObjectTypeTable} and 'data' node.
 * @since 0.1
 */
public class ObjectsWithTableData extends ObjectsFiltered<DbdTableMapping> {

    /**
     * Instantiates a new Objects are data tables.
     * @param origin The Objects to be encapsulated.
     */
    public ObjectsWithTableData(final Objects<?> origin) {
        super(
            new ObjectHasDataNodePredicate(),
            new ObjectsWithType<>(
                new ObjectTypeTable(),
                origin
            )
        );
    }

}
