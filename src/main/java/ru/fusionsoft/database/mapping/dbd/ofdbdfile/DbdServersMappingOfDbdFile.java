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
package ru.fusionsoft.database.mapping.dbd.ofdbdfile;

import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.DbdServersMapping;
import ru.fusionsoft.database.mapping.fields.DbdRootFields;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The {@link DbdServersMapping} that can be constructed of {@link DbdReadable}.
 * @since 0.1
 */
public class DbdServersMappingOfDbdFile extends DbdServersMapping {

    /**
     * Instantiates a new {@link DbdServersMapping}, taken from {@link DbdReadable}.
     * @param dbdfile The {@link DbdReadable} to be encapsulated.
     */
    public DbdServersMappingOfDbdFile(final DbdReadable dbdfile) {
        super(
            new YamlMappingOfScalar(
                () -> new YamlMappingOfPath(
                    dbdfile.asYaml(),
                    DbdRootFields.SERVERS
                )
            )
        );
    }

}
