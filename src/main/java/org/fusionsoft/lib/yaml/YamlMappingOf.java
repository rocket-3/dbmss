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
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.YamlInput;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.scalar.Sticky;
import org.fusionsoft.database.YamlRepresentative;

/**
 * The type of on-demand YamlMapping that can be constructed of YamlInput.
 * @since 0.1
 */
public class YamlMappingOf extends YamlMappingEnvelope {

    /**
     * Instantiates a new YamlMapping of YamlNode, that is used on demand.
     * @param input The YamlNode to be used.
     */
    public YamlMappingOf(final YamlNode input) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    input::asMapping
                )
            )
        );
    }

    /**
     * Instantiates a new YamlMapping of YamlInput, that is used on demand.
     * @param input The YamlInput to be used.
     */
    public YamlMappingOf(final YamlInput input) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    input::readYamlMapping
                )
            )
        );
    }

    /**
     * Instantiates a new YamlMapping of YamlRepresentative,
     *  that is used on demand.
     * @param input The YamlRepresentative to be used.
     */
    public YamlMappingOf(final YamlRepresentative input) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    () -> input.asYaml().asMapping()
                )
            )
        );
    }

}
