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

import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link YamlMappingOfEntries} with {@link DbdTableMapping}'s
 *  that can be constructed of {@link Objects}
 *  and filtered by schema.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class DbdTablesMappingOfObjects extends MappingOfObjectsOfParentAndType {

    /**
     * Instantiates a new Dbd tables mapping of objects.
     * @param objects The all Objects to be encapsulated.
     * @param schema The parent schema DbObject to be encapsulated.
     */
    public DbdTablesMappingOfObjects(
        final Objects<?>objects,
        final DbObject<?> schema
    ) {
        super(
            objects,
            schema,
            new ObjectTypeTable(),
            DbdTableMappingOfObjects::new
        );
    }

}
