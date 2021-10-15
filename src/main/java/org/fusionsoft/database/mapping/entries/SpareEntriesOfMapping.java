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
package org.fusionsoft.database.mapping.entries;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.YamlRepresentative;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import org.fusionsoft.lib.yaml.artefacts.MappingFromMappingIgnoreKeys;

/**
 * The Iterable of Map.Entry of Text -> YamlNode of that can be constructed of
 *  YamlMapping and iterable of keys to exclude.
 * @since 0.1
 */
public class SpareEntriesOfMapping extends IterableEnvelope<MapEntry<Text, ? extends YamlNode>> {

    /**
     * Instantiates a new Spare entries of mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param excludekeys The Iterable of Text representation of keys to be excluded.
     */
    public SpareEntriesOfMapping(final YamlMapping mapping, final Iterable<Text> excludekeys) {
        super(
            new EntriesOfYamlMapping(
                new MappingFromMappingIgnoreKeys(
                    mapping,
                    excludekeys
                )
            )
        );
    }

    /**
     * Instantiates a new Spare entries of mapping.
     * @param representative The YamlRepresentative<?> to be encapsulated.
     * @param keys The Iterable of Text to be encapsulated.
     */
    public SpareEntriesOfMapping(
        final YamlRepresentative<?> representative,
        final Iterable<Text> keys
    ) {
        this(new MappingOfRepresentative(representative), keys);
    }

}
