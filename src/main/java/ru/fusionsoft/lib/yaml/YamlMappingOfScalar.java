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

import com.amihaiemil.eoyaml.BaseYamlMapping;
import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The type of YamlMapping that can be constructed of {@link Scalar}
 *  for on-demand usage pattern.
 * @since 0.1
 */
public class YamlMappingOfScalar extends BaseYamlMapping {

    /**
     * The Unchecked Scalar of YamlMapping encapsulated.
     */
    private final Unchecked<YamlMapping> scalar;

    /**
     * Instantiates a new Yaml mapping of scalar.
     * @param scalar The Scalar of YamlMapping to be encapsulated.
     */
    public YamlMappingOfScalar(final Scalar<? extends YamlMapping> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar::value));
    }

    @Override
    public final Set<YamlNode> keys() {
        return this.scalar.value().keys();
    }

    @Override
    public final YamlNode value(final YamlNode key) {
        return this.scalar.value().value(key);
    }

    @Override
    public final Comment comment() {
        return this.scalar.value().comment();
    }

}
