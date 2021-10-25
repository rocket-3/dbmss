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

import org.cactoos.Text;
import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.mapping.fields.DbdRootFields;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;

/**
 * The {@link DbdServerMapping} of {@link DbdFile}.
 * @since 0.1
 */
public class DbdServerMappingOfDbdFile extends DbdServerMapping {

    /**
     * Instantiates a new Dbd server mapping of dbd file.
     * @param file The DbdFile to be encapsulated.
     * @param name The Text of server name to be encapsulated.
     */
    public DbdServerMappingOfDbdFile(final DbdFile file, final Text name) {
        super(
            new DbdServerMapping(
                new YamlMappingOfPath(
                    file.asYaml(),
                    DbdRootFields.SERVERS, name
                )
            )
        );
    }

}
