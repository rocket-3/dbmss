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
package org.fusionsoft.database.mapping.dbd.ofobjects;

import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.dbd.DbdSequenceMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The type of {@link DbdSequenceMapping} that can be constructed
 *  of sequence {@link DbObject}.
 * @since 0.1
 */
public class DbdSequenceMappingOfObjects extends DbdSequenceMapping {

    /**
     * Instantiates a new Dbd sequence mapping of objects.
     * @param objects The Objects to be encapsulated.
     * @param sequence The sequence DbObject to be encapsulated.
     */
    @SuppressWarnings("PMD.UnusedFormalParameter")
    public DbdSequenceMappingOfObjects(final Objects objects, final DbObject<?> sequence) {
        super(
            new MappingOfRepresentative(sequence)
        );
    }

}