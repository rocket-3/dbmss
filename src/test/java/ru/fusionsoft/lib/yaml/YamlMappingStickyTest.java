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

import org.cactoos.text.Joined;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link YamlMappingSticky}.
 * @since 0.1
 */
final class YamlMappingStickyTest {

    /**
     * Main.
     */
    @Test
    @SuppressWarnings("PMD")
    public void main() {
        System.out.println(
            new YamlMappingSticky(
                new YamlMappingOf(
                    new Joined(
                        "\n",
                        "key:",
                        "  nested:",
                        "    a: 4",
                        "    b: ",
                        "      - 1",
                        "      - 2",
                        "      # something about sequence value",
                        "      -",
                        "        # something about 'a'",
                        "        a:",
                        "          # something about 'one'",
                        "          one: abc #something about 'abc'",
                        "          # something about 'two'",
                        "          two: bca #something about 'bca'",
                        "          three: ",
                        "            bca: ''",
                        "        e: ",
                        "          - f",
                        "          - g",
                        "          - h: 1"
                    )
                )
            ).toString()
        );
    }

}
