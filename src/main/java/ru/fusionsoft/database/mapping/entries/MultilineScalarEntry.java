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
package ru.fusionsoft.database.mapping.entries;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.lib.exception.NotImplemented;

/**
 * The {@link Map.Entry} of {@link Text}, {@link YamlNode} with text that should
 *  be rendered as multiline text.
 * @since 0.1
 */
public class MultilineScalarEntry implements Map.Entry<Text, YamlNode> {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The Scalar of YamlNode encapsulated.
     */
    private final Unchecked<YamlNode> node;

    /**
     * Instantiates a new Multiline Scalar entry.
     * @param text The Text to be encapsulated.
     * @param node The Scalar of YamlNode to be encapsulated.
     */
    private MultilineScalarEntry(final Text text, final Scalar<YamlNode> node) {
        this.text = text;
        this.node = new Unchecked<>(new Sticky<>(node));
    }

    /**
     * Instantiates a new Multiline Scalar entry.
     * @param key The Text to be encapsulated.
     * @param value The Text to be encapsulated.
     */
    public MultilineScalarEntry(final Text key, final Text value) {
        this(
            key,
            () -> Yaml.createYamlScalarBuilder().addLine(
                new TextOfScalar(
                    () -> {
                        String txt = value.asString();
                        if (!txt.contains(System.lineSeparator())) {
                            txt = txt.replace("\n", System.lineSeparator());
                        }
                        return txt;
                    }
                ).asString()
            ).buildLiteralBlockScalar()
        );
    }

    /**
     * Instantiates a new Multiline Scalar entry.
     * @param key The String to be encapsulated.
     * @param value The String to be encapsulated.
     */
    public MultilineScalarEntry(final String key, final String value) {
        this(new TextOf(key), new TextOf(value));
    }

    @Override
    public final Text getKey() {
        return this.text;
    }

    @Override
    public final YamlNode getValue() {
        return this.node.value();
    }

    @Override
    public final YamlNode setValue(final YamlNode value) {
        throw new NotImplemented();
    }

}
