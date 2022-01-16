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

import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdFile;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The type of {@link Objects} that represents objects filtered by being
 *  mentioned as tables in {@link DbdFile} presented.
 * @since 0.1
 */
public class ObjectsAreTablesInDbdFile extends ObjectsMentionedIn<DbdTableMapping> {

    /**
     * Instantiates a new objects are tables in dbd file.
     * @param origin The original {@link Objects} to be encapsulated.
     * @param file The {@link DbdFile} to be encapsulated.
     */
    public ObjectsAreTablesInDbdFile(final Objects<?> origin, final DbdFile file) {
        super(
            new ObjectsWithType<>(
                new ObjectTypeTable(),
                new ObjectsOfDbdFile(file)
            ),
            new ObjectsWithType<>(
                new ObjectTypeTable(),
                origin
            )
        );
    }

}
