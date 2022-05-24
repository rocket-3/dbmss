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

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsText;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

class YamlMappingOfEntriesTest {

    @Test
    public void latterMathingEntriesRewritesPrevious() {
        final Text kfirst = () -> "kfirst";
        final Text ksecond = () -> "ksecond";
        final Text vfirst = () -> "vfirst";
        final Text vsecond = () -> "vsecond";
        new Assertion<Text>(
            "Should have vfirst rewrited",
            new TextOfYamlMappingKeyValue(
                new YamlMappingOfPath(
                    new YamlMappingOfEntries(
                        new MapEntry<>(
                            kfirst,
                            new YamlMappingOfEntries(
                                new ScalarEntry(
                                    ksecond,
                                    vfirst
                                )
                            )
                        ),
                        new MapEntry<>(
                            kfirst,
                            new YamlMappingOfEntries(
                                new ScalarEntry(
                                    ksecond,
                                    vsecond
                                )
                            )
                        )
                    ),
                    kfirst
                ),
                ksecond
            ),
            new IsText(vsecond)
        ).affirm();
    }

}
