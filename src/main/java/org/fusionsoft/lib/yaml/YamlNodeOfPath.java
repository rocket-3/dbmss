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
import org.cactoos.func.Chained;
import org.cactoos.func.FuncOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.TextOf;

/**
 * Yaml node of YamlMapping by given path.
 * @since 0.1
 */
public class YamlNodeOfPath extends YamlNodeOfScalar {

    /**
     * Primary ctor. of class.
     * @param mapping The source of searched node.
     * @param paths The paths to go through.
     */
    public YamlNodeOfPath(
        final YamlNode mapping,
        final Iterable<Text> paths
    ) {
        super(() -> {
            return new Chained<YamlNode, YamlNode, YamlNode>(
                new FuncOf<>(YamlNode::asMapping),
                new Mapped<>(
                    path -> new FuncOf<>(
                        node -> new StrictYamlMapping(
                            node.asMapping()
                        ).value(path.asString())
                    ),
                    paths
                ),
                new FuncOf<>(node -> node)
            ).apply(mapping);
        });
    }

    /**
     * Primary ctor. of class.
     * @param mapping The source of searched node.
     * @param paths The paths to go through.
     */
    public YamlNodeOfPath(
        final YamlNode mapping,
        final Text... paths
    ) {
        this(mapping, new IterableOf<Text>(paths));
    }

    /**
     * Primary ctor. of class.
     * @param mapping The source of searched node.
     * @param paths The paths to go through.
     */
    public YamlNodeOfPath(
        final YamlNode mapping,
        final String... paths
    ) {
        this(mapping, new Mapped<Text>(TextOf::new, paths));
    }

}
