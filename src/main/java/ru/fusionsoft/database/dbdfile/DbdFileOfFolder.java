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
package ru.fusionsoft.database.dbdfile;

import ru.fusionsoft.database.Folder;
import ru.fusionsoft.database.text.DbdFileName;
import ru.fusionsoft.lib.yaml.YamlMappingOf;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The {@link ru.fusionsoft.database.DbdFile} that is taken from {@link Folder}.
 * @since 0.1
 */
public class DbdFileOfFolder extends DbdFileOfMapping {

    /**
     * Instantiates a new Dbd file of mapping.
     * @param folder The {@link Folder} to be encapsulated.
     */
    public DbdFileOfFolder(final Folder folder) {
        super(
            new YamlMappingOfScalar(
                () -> new YamlMappingOf(
                    folder.path().resolve(
                        new DbdFileName().asString()
                    )
                )
            )
        );
    }

}
