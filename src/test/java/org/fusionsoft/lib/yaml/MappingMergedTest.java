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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Iterator;
import java.util.Map;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.map.MapEntry;
import org.cactoos.scalar.LengthOf;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.lib.text.JsonUndefinedText;
import org.fusionsoft.lib.yaml.artefacts.KeysFromYamlNode;
import org.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.IsText;

/**
 * The test for {@link MappingMerged}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
class MappingMergedTest {

    /**
     * Adds entries.
     * @throws Exception When can't.
     */
    @Test
    public void addsEntries() throws Exception {
        final Iterable<Map.Entry<Text, YamlNode>> first = new IterableOf<>(
            new ScalarEntry("a", "bc"),
            new ScalarEntry("d", "ef")
        );
        final Iterable<Map.Entry<Text, YamlNode>> second = new IterableOf<>(
            new ScalarEntry("g", "he")
        );
        new Assertion<>(
            "Should have all the entries from both sets",
            new EntriesOfYamlMapping(
                new MappingMerged(
                    new YamlMappingOfEntries(first),
                    new YamlMappingOfEntries(second)
                )
            ),
            new HasSize(
                new LengthOf(
                    new Joined<Map.Entry<Text, YamlNode>>(
                        first,
                        second
                    )
                ).value().intValue()
            )
        ).affirm();
    }

    /**
     * Replaces entries.
     * @checkstyle LocalFinalVariableNameCheck (200 lines)
     */
    @Test
    public void replacesEntries() {
        final String key = "a";
        final String value = "de";
        final ScalarEntry first = new ScalarEntry(key, "bc");
        final ScalarEntry second = new ScalarEntry(key, value);
        new Assertion<>(
            "Should have entry from second set exactly",
            new TextOfMappingValue(
                new MappingMerged(
                    new YamlMappingOfEntries(first),
                    new YamlMappingOfEntries(second)
                ),
                key
            ),
            new IsText(value)
        ).affirm();
    }

    /**
     * Replaces conflicting deep entry.
     * @throws Exception When can't.
     */
    @Test
    public void replacesConflictingDeepEntry() throws Exception {
        final Iterable<Text> paths = new IterableOf<>(
            new TextOf("a"),
            new TextOf("b"),
            new TextOf("c")
        );
        final Func<Text, YamlMapping> withDeepValue = (Text value) -> {
            final Iterator<Text> path = paths.iterator();
            return new YamlMappingOfEntries(
                new MapEntry<Text, YamlNode>(
                    path.next(),
                    new YamlMappingOfEntries(
                        new MapEntry<>(
                            path.next(),
                            new YamlMappingOfEntries(
                                new ScalarEntry(
                                    path.next(),
                                    value
                                )
                            )
                        )
                    )
                )
            );
        };
        final TextOf second = new TextOf("2");
        new Assertion<>(
            "Entry should have the with second value exactly",
            new TextOfScalarNode(
                new YamlNodeOfPath(
                    new MappingMerged(
                        withDeepValue.apply(new TextOf("1")),
                        withDeepValue.apply(second)
                    ),
                    paths
                )
            ),
            new IsText(second)
        ).affirm();
    }

    /**
     * Keeps unmentioned deep entry.
     * @throws Exception When can't.
     */
    @Test
    public void keepsUnmentionedDeepEntry() throws Exception {
        final Iterable<Text> paths = new IterableOf<>(
            new TextOf("a"),
            new TextOf("b")
        );
        final Func<Text, YamlMapping> withDeepValue = (Text key) -> {
            final Iterator<Text> path = paths.iterator();
            return new YamlMappingOfEntries(
                new MapEntry<Text, YamlNode>(
                    path.next(),
                    new YamlMappingOfEntries(
                        new MapEntry<>(
                            path.next(),
                            new YamlMappingOfEntries(
                                new ScalarEntry(
                                    key,
                                    new TextOf("nothing")
                                )
                            )
                        )
                    )
                )
            );
        };
        final TextOf first = new TextOf("1");
        final TextOf second = new TextOf("2");
        new Assertion<>(
            "Should keep both entries",
            new KeysFromYamlNode(
                new YamlNodeOfPath(
                    new MappingMerged(
                        withDeepValue.apply(first),
                        withDeepValue.apply(second)
                    ),
                    paths
                )
            ),
            new HasSize(new SetOf<>(first, second).size())
        ).affirm();
    }

    /**
     * Replaces the value even if changed one is null in terms of JSON.
     * @checkstyle LocalFinalVariableNameCheck (200 lines)
     */
    @Test
    public void replacesToNull() {
        final String key = "a";
        final String value = "b";
        final ScalarEntry first = new ScalarEntry(key, value);
        final ScalarEntry second = new ScalarEntry(
            new TextOf(key),
            new JsonUndefinedText()
        );
        new Assertion<>(
            "Should have entry from second set exactly",
            new TextOfMappingValue(
                new MappingMerged(
                    new YamlMappingOfEntries(first),
                    new MappingWithoutEmptyScalars(
                        new YamlMappingOfEntries(
                            second,
                            new MapEntry<>(
                                new TextOf("b"),
                                new YamlMappingOfEntries(second)
                            )
                        )
                    )
                ),
                key
            ),
            new IsText(new JsonUndefinedText())
        ).affirm();
    }
}
