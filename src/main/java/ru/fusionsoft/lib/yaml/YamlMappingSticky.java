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

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.scalar.Sticky;

/**
 * The {@link YamlMapping} which pre-renders all nodes, i.e. forced eager evaluation.
 * @since 0.1
 */
public class YamlMappingSticky extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping sticky.
     * @param origin The origin
     */
    public YamlMappingSticky(final YamlMapping origin) {
        super(
            new Sticky<>(
                () -> new YamlRepresentativeSticky(origin).asYaml().asMapping()
            )
        );
    }

}
