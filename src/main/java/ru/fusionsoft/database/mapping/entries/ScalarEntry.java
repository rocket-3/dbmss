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
import org.cactoos.Text;
import org.cactoos.text.TextOf;

/**
 * The type of Map.Entry of Text -> Text that can be constructed of
 *  Text or String.
 * @since 0.1
 */
public class ScalarEntry extends YamlMappingEntryOfScalar<YamlNode> {

    /**
     * Instantiates a new Scalar entry.
     * @param key The Text to be encapsulated.
     * @param value The Text to be encapsulated.
     */
    public ScalarEntry(final Text key, final Text value) {
        super(
            key,
            () -> Yaml.createYamlScalarBuilder().addLine(value.asString()).buildPlainScalar()
        );
    }

    /**
     * Instantiates a new Scalar entry.
     * @param key The String to be encapsulated.
     * @param value The String to be encapsulated.
     */
    public ScalarEntry(final String key, final String value) {
        this(new TextOf(key), new TextOf(value));
    }

}
