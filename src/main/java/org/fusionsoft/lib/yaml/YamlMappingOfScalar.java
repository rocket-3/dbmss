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
 *
 */
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.BaseYamlMapping;
import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

public class YamlMappingOfScalar extends BaseYamlMapping {

    private final Unchecked<YamlMapping> scalar;

    public YamlMappingOfScalar(final Scalar<YamlMapping> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public Set<YamlNode> keys() {
        return scalar.value().keys();
    }

    @Override
    public YamlNode value(final YamlNode key) {
        return scalar.value().value(key);
    }

    @Override
    public Comment comment() {
        return scalar.value().comment();
    }

}
