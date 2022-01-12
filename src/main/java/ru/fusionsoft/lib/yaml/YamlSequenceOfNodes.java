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

import com.amihaiemil.eoyaml.BaseYamlSequence;
import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Collection;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;

/**
 * The type of YamlSequence extends YamlNode constructed of nodes.
 * @since 0.1
 */
public class YamlSequenceOfNodes extends BaseYamlSequence {

    /**
     * The Collection of YamlNode encapsulated.
     */
    private final Collection<YamlNode> collection;

    /**
     * Instantiates a new Yaml sequence of.
     * @param nodes The Iterable of YamlNode to be encapsulated.
     */
    public YamlSequenceOfNodes(final Iterable<? extends YamlNode> nodes) {
        this.collection = new ListOf<YamlNode>(nodes);
    }

    /**
     * Instantiates a new Yaml sequence of.
     * @param nodes The YamlNode's array to be encapsulated.
     */
    public YamlSequenceOfNodes(final YamlNode... nodes) {
        this(new IterableOf<YamlNode>(nodes));
    }

    @Override
    public final Collection<YamlNode> values() {
        return this.collection;
    }

    @Override
    public final Comment comment() {
        return new Comment() {
            @Override
            public YamlNode yamlNode() {
                return YamlSequenceOfNodes.this;
            }

            @Override
            public String value() {
                return "";
            }
        };
    }

}
