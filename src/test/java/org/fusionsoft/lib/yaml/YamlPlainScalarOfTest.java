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

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.text.TextOf;
import org.fusionsoft.lib.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.Throws;

/**
 * The test for {@link YamlPlainScalarOf} class.
 * @since 0.1
 */
class YamlPlainScalarOfTest {

    /**
     * Can use to string when decorate yaml node.
     */
    @Test
    public void canUseToStringWhenDecorateYamlNode() {
        final Text text = new TextOf("ABDC");
        new Assertion<>(
            "???",
            () -> {
                throw new ValidationException(
                    new YamlMappingOfEntries(
                        new MapEntry<>(
                            text,
                            new YamlPlainScalarOf(text)
                        )
                    ).toString()
                );
            },
            new Throws<>(ValidationException.class)
        ).affirm();
    }

}
