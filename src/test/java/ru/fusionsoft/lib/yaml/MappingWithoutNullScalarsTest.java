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

import com.amihaiemil.eoyaml.YamlSequence;
import org.cactoos.map.MapEntry;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsText;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.lib.text.JsonUndefinedText;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlNode;

/**
 * The test for {@link MappingWithoutNullScalars}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class MappingWithoutNullScalarsTest {

    /**
     * Deletes first level null scalars.
     */
    @Test
    public void deletesFirstLevelNullScalars() {
        final ScalarEntry nullable = new ScalarEntry(
            new TextOf("a"),
            new JsonUndefinedText()
        );
        final ScalarEntry scalar = new ScalarEntry(
            new TextOf("b"),
            new TextOf("value")
        );
        final MapEntry<TextOf, YamlMappingOfEntries> mapping = new MapEntry<>(
            new TextOf("c"),
            new YamlMappingOfEntries(nullable)
        );
        final MapEntry<TextOf, YamlSequence> sequence = new MapEntry<>(
            new TextOf("d"),
            new YamlSequenceOfNodes()
        );
        new Assertion<>(
            "Should not contain null scalar entry",
            new TextOfYamlNode(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
                        nullable,
                        scalar,
                        mapping,
                        sequence
                    )
                )
            ),
            new IsText(
                new TextOfYamlNode(
                    new YamlMappingOfEntries(scalar, mapping)
                )
            )
        ).affirm();
    }

}
