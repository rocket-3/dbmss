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

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.lib.text.JsonEmptyText;

/**
 * The type of empty {@link com.amihaiemil.eoyaml.YamlMapping}
 *  with some keys and '' as values placed before.
 * @since 0.1
 */
public class MappingWithKeys extends YamlMappingOfEntries {

    /**
     * Instantiates a new Mapping with keys.
     * @param keys The Iterable of Text to be encapsulated.
     */
    public MappingWithKeys(final Iterable<Text> keys) {
        super(
            new Mapped<Map.Entry<? extends Text, ? extends YamlNode>>(
                key -> new ScalarEntry(key, new JsonEmptyText()),
                keys
            )
        );
    }

    /**
     * Instantiates a new Mapping with keys.
     * @param keys The Text... to be encapsulated.
     */
    public MappingWithKeys(final Text... keys) {
        this(new IterableOf<Text>(keys));
    }

}
