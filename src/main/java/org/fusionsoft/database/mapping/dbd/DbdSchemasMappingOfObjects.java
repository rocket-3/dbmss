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
package org.fusionsoft.database.mapping.dbd;

import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.ObjectType;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsWithType;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/schemas mapping of {@link Objects}.
 * @since 0.1
 */
public class DbdSchemasMappingOfObjects extends YamlMappingOfEntries {

    /**
     * Instantiates a new Dbd schemas mapping of objects.
     * @param objects The Objects to be encapsulated.
     */
    public DbdSchemasMappingOfObjects(final Objects objects) {
        super(
            new UnwrapEntriesOfObjects<>(
                objects,
                new ObjectsWithType(ObjectType.SCHEMA, objects),
                DbdSchemaMappingOfObjects::new
            )
        );
    }

}
