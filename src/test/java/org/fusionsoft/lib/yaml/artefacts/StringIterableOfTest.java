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
package org.fusionsoft.lib.yaml.artefacts;

import java.io.IOException;
import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;
import org.fusionsoft.lib.yaml.YamlInputOf;
import org.fusionsoft.lib.yaml.YamlMappingOf;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * StringIterableOf tests.
 * @since 0.1
 */
@SuppressWarnings("PMD")
class StringIterableOfTest {

    @Test
    public void canUseScalarOfText() throws IOException {
        final Text key = new TextOf("0");
        final String chars = "[0, 1, \"ACTIVE\", \"ACTIVE\",null, null]";
        System.out.println(
            new YamlMappingOfEntries(
                new MapEntry<>(
                    key,
                    new YamlInputOf(chars).readPlainScalar().asScalar()
                )
            ).toString()
        );
    }

    /**
     * Can parse YAML array sequence of {@link com.amihaiemil.eoyaml.Scalar}.
     */
    @Test
    public void canParseArraySequence() {
        Assertions.assertTrue(
            new SetOf<>(
                new StringSetOf(
                    new YamlMappingOf(
                        new YamlInputOf(
                            new StringBuilder()
                                .append("data:\n")
                                .append("  \"instanceStatus~ACTIVE\": ")
                                .append(
                                    "[0, 1, \"ACTIVE\", \"ACTIVE\",null, null]\n"
                                )
                                .append("  \"instanceStatus~DELETED\": ")
                                .append(
                                    "[0, 2, \"DELETED\", \"DELETED\", null, null]"
                                )
                                .toString()
                        )
                    )
                        .value("data")
                        .asMapping()
                        .value("\"instanceStatus~DELETED\"")
                        .asScalar()
                )
            ).containsAll(
                new SetOf<String>(
                    "0",
                    "2",
                    "\"DELETED\"",
                    "\"DELETED\"",
                    "null",
                    "null"
                )
            )
        );
    }

}
