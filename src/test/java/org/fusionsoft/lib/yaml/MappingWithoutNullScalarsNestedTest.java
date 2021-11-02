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

import org.cactoos.map.MapEntry;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.lib.text.JsonUndefinedText;
import org.fusionsoft.lib.yaml.artefacts.TextOfYamlNode;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsText;

/**
 * The test for {@link MappingWithoutNullScalarsNested}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class MappingWithoutNullScalarsNestedTest {

    /**
     * Deletes all null scalars.
     */
    @Test
    public void deletesAllNullScalars() {
        final TextOf key = new TextOf("c");
        final ScalarEntry nullable = new ScalarEntry(
            new TextOf("a"),
            new JsonUndefinedText()
        );
        final ScalarEntry scalar = new ScalarEntry(
            new TextOf("b"),
            new TextOf("value")
        );
        final MapEntry<TextOf, YamlMappingOfEntries> mapping = new MapEntry<>(
            key,
            new YamlMappingOfEntries(nullable)
        );
        new Assertion<>(
            "Should not contain entries of null scalar",
            new TextOfYamlNode(
                new MappingWithoutNullScalarsNested(
                    new YamlMappingOfEntries(
                        nullable,
                        scalar,
                        mapping
                    )
                )
            ),
            new IsText(
                new TextOfYamlNode(
                    new YamlMappingOfEntries(
                        scalar,
                        new MapEntry<>(
                            key,
                            new MappingEmpty()
                        )
                    )
                )
            )
        ).affirm();
    }

}
