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

import org.fusionsoft.database.DbdFile;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;

/**
 * The type of DbdServerMapping that can be constructed of {@link DbdFile}.
 * @since 0.1
 */
public class DbdServerMappingOfDbdFile extends DbdServerMapping {

    /**
     * Instantiates a new Dbd server mapping of dbd file.
     * @param file The DbdFile to be encapsulated.
     * @param name The String of server's name to be used.
     */
    public DbdServerMappingOfDbdFile(final DbdFile file, final String name) {
        super(
            new YamlMappingOfPath(
                file.asYaml(),
                "servers", name
            )
        );
    }

}
