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
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;

/**
 * The type of {@link YamlMapping} with some entries
 *  added/replaced to the original one.
 * @since 0.1
 */
public class MappingWithEntries extends MappingMerged {

    /**
     * Instantiates a new Mapping with entries.
     * @param original The YamlMapping to be encapsulated.
     * @param entries The Iterable of Entry of Text, YamlNode to be encapsulated.
     */
    public MappingWithEntries(
        final YamlMapping original,
        final Iterable<? extends Map.Entry<Text, YamlNode>> entries
    ) {
        super(original, new YamlMappingOfEntries(entries));
    }

    /**
     * Instantiates a new Mapping with entries.
     * @param original The YamlMapping to be encapsulated.
     * @param entries The array of Entry of Text, YamlNode to be encapsulated.
     */
    @SafeVarargs
    public MappingWithEntries(
        final YamlMapping original,
        final Map.Entry<Text, YamlNode>... entries
    ) {
        super(original, new YamlMappingOfEntries(entries));
    }

}
