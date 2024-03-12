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
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.Fallback;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.ScalarWithFallback;
import org.cactoos.scalar.Sticky;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlScalarNode;

/**
 * Yaml mapping, extracted of specific path
 *  of given {@link YamlNode} as {@link com.amihaiemil.eoyaml.YamlMapping}
 *  or empty {@link com.amihaiemil.eoyaml.YamlMapping} if path not exists.
 * @since 0.1
 */
public class YamlMappingOfPathOrEmpty extends YamlMappingEnvelope {

    /**
     * Instantiates a new Yaml mapping from {@link YamlNode} and array of
     *  subdirs to dive through, if not found, gives an empty mapping.
     * @param node The YamlNode to be encapsulated.
     * @param paths The subdirectories to dive through.
     */
    public YamlMappingOfPathOrEmpty(final YamlNode node, final Iterable<Text> paths) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    new ScalarWithFallback<>(
                        () -> {
                            final YamlMappingOfPath map = new YamlMappingOfPath(
                                node,
                                paths
                            );
                            map.keys();
                            return map;
                        },
                        new Fallback.From<>(
                            YamlNodeNotFoundException.class,
                            ex -> new MappingEmpty()
                        )
                    )
                )
            )
        );
    }

    /**
     * Instantiates a new Yaml mapping from {@link YamlNode} and array of
     *  subdirs to dive through.
     * @param node The YamlNode to be encapsulated.
     * @param paths The subdirectories to dive through.
     */
    public YamlMappingOfPathOrEmpty(final YamlNode node, final Text... paths) {
        this(
            node,
            new IterableOf<Text>(paths)
        );
    }

    /**
     * Instantiates a new Yaml mapping from {@link YamlNode} and array of
     *  subdirs to dive through.
     * @param node The YamlNode to be encapsulated.
     * @param paths The subdirectories to dive through.
     */
    public YamlMappingOfPathOrEmpty(final YamlNode node, final YamlNode... paths) {
        this(
            node,
            new Mapped<>(
                TextOfYamlScalarNode::new,
                paths
            )
        );
    }

    /**
     * Instantiates a new Yaml mapping from {@link YamlNode} and array of
     *  subdirs to dive through.
     * @param node The YamlNode to be encapsulated.
     * @param paths The subdirectories to dive through.
     */
    public YamlMappingOfPathOrEmpty(final YamlNode node, final CharSequence... paths) {
        this(
            node,
            new Mapped<Text>(
                TextOf::new,
                paths
            )
        );
    }

}
