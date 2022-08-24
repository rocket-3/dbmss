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
import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The type of {@link Iterable} of {@link DbObject}s that represents objects filtered by being
 *  mentioned as configuration tables in {@link DbdReadable} presented.
 * @since 0.1
 */
public class ObjectsWithDataMentionedInDbdFile extends ObjectsMentionedInObjects<DbdTableMapping> {

    /**
     * Instantiates a new Configuration tables of dbd.
     * @param origin The original {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param file The {@link DbdReadable} to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> ObjectsWithDataMentionedInDbdFile(
        final Iterable<DbObject<Y>> origin,
        final DbdReadable file
    ) {
        super(
            new ObjectsWithTableData(
                new ObjectsOfDbdReadable(file)
            ),
            new ObjectsWithTypeCasted<>(
                new ObjectTypeTable(),
                origin
            )
        );
    }

}
