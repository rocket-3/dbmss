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

import org.cactoos.Text;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;

/**
 * The type of {@link com.amihaiemil.eoyaml.YamlMapping}
 *  that can be constructed of some resource by its name.
 * @since 0.1
 */
public class MappingOfResource extends YamlMappingOf {

    /**
     * Instantiates a new Mapping of resource.
     * @param resource The Text to be encapsulated.
     */
    public MappingOfResource(final Text resource) {
        super(new YamlInputOf(new ResourceOf(resource)));
    }

    /**
     * Instantiates a new Mapping of resource.
     * @param resource The CharSequence to be encapsulated.
     */
    public MappingOfResource(final CharSequence resource) {
        this(new TextOf(resource));
    }

}
