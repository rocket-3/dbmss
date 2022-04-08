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

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Set;
import org.cactoos.Text;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The type of Text that can be constructed of value from
 *  {@link YamlMapping} and its key, which may be absent and empty value is
 *   returned in that case.
 * @since 0.1
 */
public class MaybeEmptyTextOfYamlMapping implements Text {

    /**
     * The {@link UncheckedText} encapsulated.
     */
    private final UncheckedText text;

    /**
     * Instantiates a new Maybe empty text of.
     * @param mapping The StrictYamlMapping to be encapsulated.
     * @param key The String to be encapsulated.
     */
    public MaybeEmptyTextOfYamlMapping(
        final StrictYamlMapping mapping,
        final Text key
    ) {
        this.text = new UncheckedText(
            new TextOf(
                new ScalarOf<>(
                    () -> {
                        String value = "";
                        final Set<String> keys = new KeysFromYamlNode(mapping);
                        if (keys.contains(key.asString())) {
                            final YamlNode node = mapping.value(key.asString());
                            value = node.asScalar().value();
                        }
                        return value;
                    }
                )
            )
        );
    }

    /**
     * Instantiates a new Maybe empty text of.
     * @param mapping The YamlMapping to be encapsulated.
     * @param key The String to be encapsulated.
     */
    public MaybeEmptyTextOfYamlMapping(
        final YamlMapping mapping,
        final Text key
    ) {
        this(new StrictYamlMapping(mapping), key);
    }

    @Override
    public final String asString() {
        return this.text.asString();
    }

}
