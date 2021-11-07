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
import org.fusionsoft.database.mapping.dbd.DbdFunctionMapping;
import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The mapping of DBD/schemas/#schema/functions document node, that can be
 *  constructed of all context {@link Objects} and current schema {@link DbObject}.
 * @since 0.1
 */
public class DbdFunctionsMappingOfObjects extends YamlMappingOfEntries {

    /**
     * Instantiates a new Dbd functions mapping of objects.
     * @param objects The all context {@link Objects} to be encapsulated.
     * @param schema The schema {@link DbObject} to be encapsulated.
     */
    public DbdFunctionsMappingOfObjects(
        final Objects objects,
        final DbObject<?> schema
    ) {
        super(
            new UnwrapEntriesOfObjects<DbdFunctionMapping>(
                objects,
                new ObjectsWithParentAndType(
                    schema,
                    ObjectType.FUNCTION,
                    objects
                ),
                (objs, object) -> new DbdFunctionMapping(
                    new MappingOfRepresentative(object)
                )
            )
        );
    }

}
