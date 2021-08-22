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
 *
 */
package org.fusionsoft.database.text;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.IOException;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.lib.yaml.artefacts.StringIterableOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextOfExampleYamlTest {

    @Test
    public void hasContent() throws Exception {
        Assertions.assertTrue(
            new ScalarOf<>(
                text -> {
                    System.out.println("text = " + text);
                    return text.asString();
                },
                new TextOfExampleYaml()
            )
                .value()
                .length() > 0
        );
    }

    @Test
    public void canParseArraySequence() throws IOException {
        final String text =
            "data:\n"
            + "  \"instanceStatus~ACTIVE\": [0, 1, \"ACTIVE\", \"ACTIVE\",null, null]\n"
            + "  \"instanceStatus~DELETED\": [0, 2, \"DELETED\", \"DELETED\", null, null]";

        final YamlMapping data = Yaml.createYamlInput(text)
            .readYamlMapping()
            .value("data")
            .asMapping();

        for (final YamlNode key : data.keys()) {
            System.out.println(key);
            for (final String s : new StringIterableOf(data.value(key).asScalar())) {
                System.out.println(s);
            }
        }
    }

}
