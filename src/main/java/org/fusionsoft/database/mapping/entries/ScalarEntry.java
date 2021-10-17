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

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.fusionsoft.lib.exception.NotImplemented;
import org.fusionsoft.lib.yaml.YamlPlainScalarOf;

/**
 * The type of Map.Entry of Text -> YamlNode that can be constructed of
 *  Text and Scalar of YamlNode.
 * @since 0.1
 */
public class ScalarEntry implements Map.Entry<Text, YamlNode> {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The Scalar YamlNode encapsulated.
     */
    private final YamlNode node;

    /**
     * Instantiates a new Scalar entry.
     * @param text The Text to be encapsulated.
     * @param node The Scalar YamlNode to be encapsulated.
     */
    private ScalarEntry(final Text text, final YamlNode node) {
        this.text = text;
        this.node = node;
    }

    /**
     * Instantiates a new Scalar entry.
     * @param key The Text to be encapsulated.
     * @param value The Text to be encapsulated.
     */
    public ScalarEntry(final Text key, final Text value) {
        this(
            key,
            new YamlPlainScalarOf(value)
        );
    }

    @Override
    public final Text getKey() {
        return this.text;
    }

    @Override
    public final YamlNode getValue() {
        return this.node;
    }

    @Override
    public final YamlNode setValue(final YamlNode value) {
        throw new NotImplemented();
    }

}
