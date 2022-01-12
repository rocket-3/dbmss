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

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdFile;

/**
 * The type of {@link Objects} that represents objects filtered by being
 *  mentioned as configuration tables in {@link DbdFile} presented.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectsAreDataTablesInDbdFile<T extends YamlNode> extends ObjectsOfScalar<T> {

    /**
     * Instantiates a new Configuration tables of dbd.
     * @param origin The original {@link Objects} to be encapsulated.
     * @param file The {@link DbdFile} to be encapsulated.
     */
    public ObjectsAreDataTablesInDbdFile(final Objects<T> origin, final DbdFile file) {
        super(
            () -> new ObjectsMentionedIn<>(
                new DataTablesOfObjects(
                    new ObjectsOfDbdFile(file)
                ),
                origin
            )
        );
    }

}
