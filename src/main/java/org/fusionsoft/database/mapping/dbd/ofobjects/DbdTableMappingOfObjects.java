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
import org.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import org.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.dbd.DbdTriggerMapping;
import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;

/**
 * The The DBD/schemas/#schema/tables/#table node mapping of
 *  Objects context and table DbObject.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbdTableMappingOfObjects extends DbdTableMapping {

    /**
     * Instantiates a new DbdTableMapping.
     * @param objects The Objects to be encapsulated.
     * @param table The DbObject<?> to be encapsulated.
     */
    public DbdTableMappingOfObjects(
        final Objects objects,
        final DbObject<?> table
    ) {
        super(
            new DbdTableMappingWithChildObjects(
                new DbdTableMapping(
                    new MappingOfRepresentative(table)
                ),
                new UnwrapEntriesOfObjects<>(
                    objects,
                    new ObjectsWithParentAndType(
                        table,
                        ObjectType.INDEX,
                        objects
                    ),
                    (objs, obj) -> new DbdIndexMapping(
                        new MappingOfRepresentative(obj)
                    )
                ),
                new UnwrapEntriesOfObjects<>(
                    objects,
                    new ObjectsWithParentAndType(
                        table,
                        ObjectType.CONSTRAINT,
                        objects
                    ),
                    (objs, obj) -> new DbdConstraintMapping(
                        new MappingOfRepresentative(obj)
                    )
                ),
                new UnwrapEntriesOfObjects<>(
                    objects,
                    new ObjectsWithParentAndType(
                        table,
                        ObjectType.TRIGGER,
                        objects
                    ),
                    (objs, obj) -> new DbdTriggerMapping(
                        new MappingOfRepresentative(obj)
                    )
                )
            )
        );
    }

}
