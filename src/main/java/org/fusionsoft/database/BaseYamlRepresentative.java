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
package org.fusionsoft.database;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Scalar;
import org.fusionsoft.lib.yaml.YamlNodeOfScalar;

/**
 * The type of YamlRepresentative that provides its functionality
 *  in the most straightforward way.
 * @since 0.1
 */
public class BaseYamlRepresentative implements YamlRepresentative {

    /**
     * The YamlNode encapsulated.
     */
    private final YamlNode yaml;

    /**
     * Instantiates a new YamlRepresentative of YamlNode.
     * @param yaml The YamlNode to be encapsulated.
     */
    public BaseYamlRepresentative(final YamlNode yaml) {
        this.yaml = yaml;
    }

    /**
     * Instantiates a new YamlRepresentative of YamlNode's scalar.
     * @param scalar The Scalar of YamlNode to be used.
     */
    public BaseYamlRepresentative(final Scalar<YamlNode> scalar) {
        this(new YamlNodeOfScalar(scalar));
    }

    @Override
    public final YamlNode asYaml() {
        return this.yaml;
    }

}
