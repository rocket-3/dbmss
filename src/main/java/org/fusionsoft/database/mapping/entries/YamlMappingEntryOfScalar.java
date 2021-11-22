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
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.lib.exception.NotImplemented;

public class YamlMappingEntryOfScalar<T extends YamlNode> implements Map.Entry<Text, T> {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The Scalar of YamlNode encapsulated.
     */
    private final Unchecked<T> node;

    /**
     * Instantiates a new Scalar entry.
     * @param text The Text to be encapsulated.
     * @param node The Scalar of YamlNode to be encapsulated.
     */
    public YamlMappingEntryOfScalar(final Text text, final Scalar<T> node) {
        this.text = text;
        this.node = new Unchecked<>(new Sticky<>(node));
    }

    @Override
    public final Text getKey() {
        return this.text;
    }

    @Override
    public final T getValue() {
        return this.node.value();
    }

    @Override
    public final T setValue(final YamlNode value) {
        throw new NotImplemented();
    }

}
