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

/**
 * The interface representing a thing, can be represented as {@link YamlNode}.
 * @param <Y> The type of YamlNode parameter.
 * @since 0.1
 */
public interface YamlRepresentative<Y extends YamlNode> {

    /**
     * Represent object as yaml node.
     * @return The yaml node.
     */
    Y asYaml();

}
