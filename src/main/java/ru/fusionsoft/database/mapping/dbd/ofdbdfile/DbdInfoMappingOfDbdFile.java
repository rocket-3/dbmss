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
import ru.fusionsoft.database.mapping.dbd.DbdInfoMapping;
import ru.fusionsoft.database.mapping.fields.DbdRootFields;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;

/**
 * The ctor of {@link DbdInfoMapping} from {@link DbdReadable}.
 * @since 0.1
 */
public class DbdInfoMappingOfDbdFile extends DbdInfoMapping {

    /**
     * Instantiates a new DbdInfoMapping of DbdFile.
     * @param file The YamlMapping to be encapsulated.
     */
    public DbdInfoMappingOfDbdFile(final DbdReadable file) {
        super(
            new YamlMappingOfPath(
                file.asYaml(),
                DbdRootFields.INFO
            )
        );
    }

}
