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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Proc;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.Sticky;

/**
 * The type of YamlNode that is validated over {@link Proc}.
 * @since 0.1
 */
public class YamlNodeValidated extends YamlNodeOfScalar {

    /**
     * Instantiates a new Yaml node validated.
     * @param validation The Proc of YamlNode to be used to validate.
     * @param node The YamlNode to be encapsulated.
     */
    public YamlNodeValidated(
        final Proc<YamlNode> validation,
        final YamlNode node
    ) {
        super(
            new Sticky<>(
                new ScalarOf<>(validation, node, node)
            )
        );
    }

}
