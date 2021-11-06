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
package org.fusionsoft.database.mapping.dbd.built;

import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.dbd.DbdInfoMapping;
import org.fusionsoft.database.mapping.dbd.DbdRootMapping;
import org.fusionsoft.database.mapping.dbd.DbdServersMapping;
import org.fusionsoft.database.mapping.dbd.TextOfDbdDefaultJsonSchemaRef;
import org.fusionsoft.database.mapping.dbd.ofobjects.DbdSchemasMappingOfObjects;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdRootFields;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The ways to construct DBD mapping.
 * @since 0.1
 */
public class DbdRootMappingBuilt extends DbdRootMapping {

    /**
     * Instantiates a new Dbd root mapping of.
     * @param databases The Iterable of DatabaseInfo to be encapsulated.
     * @param info The DbdInfoMapping to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public DbdRootMappingBuilt(
        final DbdServersMapping databases,
        final DbdInfoMapping info,
        final Objects objects
    ) {
        super(
            new YamlMappingOfEntries(
                new ScalarEntry(
                    DbdRootFields.JSONSCHEMA,
                    new TextOfDbdDefaultJsonSchemaRef()
                ),
                new MapEntry<>(
                    DbdRootFields.INFO,
                    info
                ),
                new MapEntry<>(
                    DbdRootFields.SERVERS,
                    databases
                ),
                new MapEntry<>(
                    DbdRootFields.SCHEMAS,
                    new DbdSchemasMappingOfObjects(objects)
                )
            )
        );
    }

}
