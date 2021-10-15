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

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Sticky;
import org.cactoos.text.TextOf;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The type of YamlMapping that can be constructed of {@link YamlNode} and path.
 * @since 0.1
 */
public class YamlMappingOfPath extends YamlMappingEnvelope {

    /**
     * Instantiates a new Yaml mapping from {@link YamlNode} and array of
     *  subdirs to dive through.
     * @param node The YamlNode to be encapsulated.
     * @param paths The subdirectories to dive through.
     */
    public YamlMappingOfPath(final YamlNode node, final Iterable<Text> paths) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    () -> new StrictYamlMapping(
                        new YamlNodeOfPath(
                            node,
                            new Mapped<>(
                                Text::asString,
                                paths
                            )
                        ).asMapping()
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
    public YamlMappingOfPath(final YamlNode node, final Text... paths) {
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
    public YamlMappingOfPath(final YamlNode node, final YamlNode... paths) {
        this(
            node,
            new Mapped<>(
                TextOfScalarNode::new,
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
    public YamlMappingOfPath(final YamlNode node, final CharSequence... paths) {
        this(
            node,
            new Mapped<Text>(
                TextOf::new,
                paths
            )
        );
    }

}
