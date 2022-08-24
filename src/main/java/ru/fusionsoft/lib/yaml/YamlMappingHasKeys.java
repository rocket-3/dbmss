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
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Sticky;

/**
 * The type of YamlMapping that is validated for having all keys passed in it.
 * @since 0.1
 */
public class YamlMappingHasKeys extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping has keys.
     * @param mapping The YamlMapping to be used.
     * @param keys The Iterable of Text to be checked for presence.
     */
    public YamlMappingHasKeys(
        final YamlMapping mapping,
        final Iterable<? extends Text> keys
    ) {
        super(
            new Sticky<>(
                () -> new YamlNodeValidated(
                    new YamlMappingHasKeysValidation(keys),
                    mapping
                ).asMapping()
            )
        );
    }

    /**
     * Instantiates a new Yaml mapping has keys.
     * @param mapping The YamlMapping to be used.
     * @param keys The Iterable of Text to be checked for presence.
     */
    public YamlMappingHasKeys(
        final YamlMapping mapping,
        final Text... keys
    ) {
        this(mapping, new IterableOf<>(keys));
    }

}
