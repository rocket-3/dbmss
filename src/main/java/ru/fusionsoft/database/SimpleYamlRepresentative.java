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
package ru.fusionsoft.database;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The type of YamlRepresentative that provides its functionality
 *  in the most straightforward way.
 * @param <Y> The type of YamlNode parameter.
 * @since 0.1
 */
public class SimpleYamlRepresentative<Y extends YamlNode> implements YamlRepresentative<Y> {

    /**
     * The YamlNode encapsulated.
     */
    private final Unchecked<Y> yaml;

    /**
     * Instantiates a new YamlRepresentative of YamlNode.
     * @param yaml The YamlNode to be encapsulated.
     */
    public SimpleYamlRepresentative(final Y yaml) {
        this(() -> yaml);
    }

    /**
     * Instantiates a new YamlRepresentative of YamlNode's scalar.
     * @param scalar The Scalar of YamlNode to be used.
     */
    public SimpleYamlRepresentative(final Scalar<Y> scalar) {
        this.yaml = new Unchecked<>(scalar);
    }

    @Override
    public final Y asYaml() {
        return this.yaml.value();
    }

}
