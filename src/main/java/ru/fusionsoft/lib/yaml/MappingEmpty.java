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
package ru.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Yaml;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;

/**
 * The type of YamlMapping which is just empty.
 * @since 0.1
 */
public class MappingEmpty extends MappingOfRepresentative {

    /**
     * Instantiates a new Mapping empty.
     */
    public MappingEmpty() {
        super(
            () -> Yaml.createYamlMappingBuilder().build()
        );
    }

}
