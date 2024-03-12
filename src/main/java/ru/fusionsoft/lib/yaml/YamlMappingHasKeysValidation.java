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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import ru.fusionsoft.lib.exception.ValueNotFoundException;
import ru.fusionsoft.lib.yaml.artefacts.KeysFromYamlNode;

/**
 * Validates {@link YamlNode} as {@link com.amihaiemil.eoyaml.YamlMapping} for having specific keys.
 * @since 0.1
 */
public class YamlMappingHasKeysValidation implements Proc<YamlNode> {

    /**
     * The Keys.
     */
    private final Iterable<? extends Text> keys;

    /**
     * Instantiates a new Yaml mapping has keys validation.
     * @param keys The keys
     */
    public YamlMappingHasKeysValidation(final Iterable<? extends Text> keys) {
        this.keys = keys;
    }

    /**
     * Instantiates a new Yaml mapping has keys validation.
     * @param keys The keys
     */
    public YamlMappingHasKeysValidation(final Text... keys) {
        this(new IterableOf<>(keys));
    }

    @Override
    public final void exec(final YamlNode node) throws ValueNotFoundException {
        final KeysFromYamlNode present = new KeysFromYamlNode(node.asMapping());
        final Iterable<String> missing = new Filtered<>(
            (String key) -> !present.contains(key),
            new Mapped<>(
                Text::asString,
                this.keys
            )
        );
        if (new ListOf<>(missing).size() > 0) {
            throw new ValueNotFoundException(
                String.join(", ", missing),
                node.toString()
            );
        }
    }

}
