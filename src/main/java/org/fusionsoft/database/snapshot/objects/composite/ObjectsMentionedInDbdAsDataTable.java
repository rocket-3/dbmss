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
package org.fusionsoft.database.snapshot.objects.composite;

import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.snapshot.ObjectSignatures;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import org.fusionsoft.database.snapshot.objects.dbd.ObjectsOfDbdFile;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsAreDataTables;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsMentionedIn;

/**
 * The type of {@link ObjectSignatures} that represents objects that are
 *  mentioned as configuration tables in {@link DbdFile}.
 * @since 0.1
 */
public class ObjectsMentionedInDbdAsDataTable extends ObjectsOfScalar {

    /**
     * Instantiates a new Configuration tables of dbd.
     * @param origin The original Objects to be encapsulated.
     * @param file The DbdFile to be encapsulated.
     */
    public ObjectsMentionedInDbdAsDataTable(final Objects origin, final DbdFile file) {
        super(
            () -> new ObjectsMentionedIn(
                new ObjectsAreDataTables(
                    new ObjectsOfDbdFile(file)
                ),
                origin
            )
        );
    }

}