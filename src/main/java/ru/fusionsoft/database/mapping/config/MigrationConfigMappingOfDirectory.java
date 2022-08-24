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
package ru.fusionsoft.database.mapping.config;

import org.cactoos.Text;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.yaml.YamlMappingOf;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The {@link MigrationConfigMapping} read of some directory.
 * @since 0.1
 */
public class MigrationConfigMappingOfDirectory extends MigrationConfigMapping {

    /**
     * Instantiates a new Migration config mapping of directory.
     * @param directory The {@link Directory} to be encapsulated.
     * @param name The {@link Text} to be encapsulated.
     */
    public MigrationConfigMappingOfDirectory(final Directory directory, final Text name) {
        super(
            new YamlMappingOfScalar(
                () -> new YamlMappingOf(directory.value().resolve(name.asString()))
            )
        );
    }

}
