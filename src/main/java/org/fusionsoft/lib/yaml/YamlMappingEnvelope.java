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

import com.amihaiemil.eoyaml.BaseYamlMapping;
import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;

/**
 * The type of YamlMapping that can be constructed of another.
 * @since 0.1
 */
public class YamlMappingEnvelope extends BaseYamlMapping {

    /**
     * The YamlMapping encapsulated.
     */
    private final YamlMapping mapping;

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public YamlMappingEnvelope(final YamlMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public final Set<YamlNode> keys() {
        return this.mapping.keys();
    }

    @Override
    public final YamlNode value(final YamlNode key) {
        return this.mapping.value(key);
    }

    @Override
    public final Comment comment() {
        return this.mapping.comment();
    }

}
