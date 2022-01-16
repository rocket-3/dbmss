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
package ru.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The type of Text that can be constructed of {@link YamlMapping} and its key.
 * @since 0.1
 */
public class TextOfMappingValue implements Text {

    /**
     * The YamlMapping encapsulated.
     */
    private final YamlMapping mapping;

    /**
     * The UncheckedText encapsulated.
     */
    private final UncheckedText key;

    /**
     * Instantiates a new Text of mapping value.
     * @param mapping The YamlMapping to be encapsulated.
     * @param key The String of key to be encapsulated.
     */
    public TextOfMappingValue(final YamlMapping mapping, final Text key) {
        this.mapping = mapping;
        this.key = new UncheckedText(key);
    }

    /**
     * Instantiates a new Text of mapping value.
     * @param mapping The YamlMapping to be encapsulated.
     * @param key The String of key to be encapsulated.
     */
    public TextOfMappingValue(final YamlMapping mapping, final String key) {
        this(mapping, new TextOf(key));
    }

    @Override
    public String asString() {
        return mapping.value(key.asString()).asScalar().value();
    }

}
