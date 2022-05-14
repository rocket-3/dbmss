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
package ru.fusionsoft.database.mapping.dbd.ofobjects;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdTableMappingOfEntries;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;

/**
 * The The DBD/schemas/#schema/tables/#table node mapping of
 *  Objects context and table DbObject.
 * @since 0.1
 */
public class DbdTableMappingOfObjects extends DbdTableMappingOfEntries {

    /**
     * Instantiates a new DbdTableMapping.
     * @param objects The whole Objects to be encapsulated.
     * @param table The parent DbObject to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> DbdTableMappingOfObjects(
        final Iterable<DbObject<Y>> objects,
        final DbObject<DbdTableMapping> table
    ) {
        super(
            new DbdTableMapping(
                new MappingOfRepresentative(table)
            ),
            new EntriesOfObjectsOfParentAndType<>(
                objects,
                table,
                new ObjectTypeIndex()
            ),
            new EntriesOfObjectsOfParentAndType<>(
                objects,
                table,
                new ObjectTypeConstraint()
            ),
            new EntriesOfObjectsOfParentAndType<>(
                objects,
                table,
                new ObjectTypeTrigger()
            ),
            new DbdDataMappingOfObjectsOrEmpty(
                objects,
                table
            )
        );
    }

}
