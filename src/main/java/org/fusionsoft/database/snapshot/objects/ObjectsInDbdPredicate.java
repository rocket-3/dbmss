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
package org.fusionsoft.database.snapshot.objects;

import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objectsignature.NamesOfObjects;

/**
 * The predicate of {@link DbObject} which tests it presents in DBD file.
 * @since 0.1
 */
public class ObjectsInDbdPredicate extends ObjectsWithNamesPredicate {

    /**
     * Instantiates a new Objects in dbd predicate.
     * @param file The DbdFile to be encapsulated.
     */
    public ObjectsInDbdPredicate(final DbdFile file) {
        super(
            new NamesOfObjects(
                new ObjectsOfDbdFile(file)
            )
        );
    }

}
