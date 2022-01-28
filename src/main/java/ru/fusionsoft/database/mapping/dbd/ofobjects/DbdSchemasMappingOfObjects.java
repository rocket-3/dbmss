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

import ru.fusionsoft.database.mapping.dbd.DbdSchemaMapping;
import ru.fusionsoft.database.mapping.dbd.DbdSchemasMapping;
import ru.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSchema;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/schemas mapping of {@link Objects}.
 * @since 0.1
 */
public class DbdSchemasMappingOfObjects extends DbdSchemasMapping {

    /**
     * Instantiates a new Dbd schemas mapping of objects.
     * @param objects The Objects to be encapsulated.
     */
    public DbdSchemasMappingOfObjects(final Objects<?> objects) {
        super(
            new YamlMappingOfEntries(
                new UnwrapEntriesOfObjects<DbdSchemaMapping>(
                    objects,
                    new ObjectsWithType<>(
                        new ObjectTypeSchema(),
                        objects
                    ),
                    DbdSchemaMappingOfObjects::new
                )
            )
        );
    }

}
