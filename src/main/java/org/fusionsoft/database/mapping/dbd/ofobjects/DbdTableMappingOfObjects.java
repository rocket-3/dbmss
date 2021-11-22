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

import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.dbd.DbdDataMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeData;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import org.fusionsoft.lib.collection.SingleOrEmptyFallback;
import org.fusionsoft.lib.yaml.MappingEmpty;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The The DBD/schemas/#schema/tables/#table node mapping of
 *  Objects context and table DbObject.
 * @since 0.1
 */
public class DbdTableMappingOfObjects extends DbdTableMappingOfEntries {

    /**
     * Instantiates a new DbdTableMapping.
     * @param objects The Objects to be encapsulated.
     * @param table The DbObject<?> to be encapsulated.
     */
    public DbdTableMappingOfObjects(
        final Objects<?> objects,
        final DbObject<?> table
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
            new DbdDataMapping(
                new YamlMappingOfScalar(
                    new SingleOrEmptyFallback<>(
                        new Mapped<>(
                            MapEntry::getValue,
                            new EntriesOfObjectsOfParentAndType<>(
                                objects,
                                table,
                                new ObjectTypeData()
                            )
                        ),
                        new MappingEmpty()
                    )
                )
            )
        );
    }

}
