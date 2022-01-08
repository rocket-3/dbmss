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
package org.fusionsoft.lib.yaml.artefacts;

import java.io.IOException;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.text.TextOf;
import org.fusionsoft.lib.yaml.YamlInputOf;
import org.fusionsoft.lib.yaml.YamlMappingOf;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlNodeOfPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Tests for {@link TextIterableOfFlowYamlSequence} tests.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
@SuppressWarnings("PMD")
class TextIterableOfFlowYamlSequenceTest {

    @Test
    @Disabled
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
     * Can parse YAML flow sequence of {@link com.amihaiemil.eoyaml.Scalar}.
     */
    @Test
    public void canParseArraySequence() {
        new Assertion<>(
            "Should have exact list of unescaped values",
            new Mapped<String>(
                Text::asString,
                new TextIterableOfFlowYamlSequence(
                    new YamlNodeOfPath(
                        new YamlMappingOf(
                            new YamlInputOf(
                                String.join(
                                    "",
                                    "data:\n",
                                    "  \"instanceStatus~ACTIVE\": ",
                                    "[0, 1, \"ACTIVE\", \"ACTIVE\",null, null]\n",
                                    "  \"instanceStatus~DELETED\": ",
                                    "[0, 2, \"D\\nELE\\TED,\", \"DEL\"\",\"\"ETED\", null, null]"
                                )
                            )
                        ),
                        "data",
                        "\"instanceStatus~DELETED\""
                    )
                )
            ),
            new HasValues<>(
                "0",
                "2",
                "D\nELE\\TED,",
                "DEL\",\"ETED",
                "null",
                "null"
            )
        ).affirm();
    }

}
