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
package ru.fusionsoft.database.mapping;

import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.Satisfies;

/**
 * The test for {@link MappingOfExampleYaml}.
 * @since 0.1
 * @checkstyle MagicNumberCheck (100 lines)
 */
class MappingOfExampleYamlTest {

    @Test
    public void renders() {
        new Assertion<>(
            "Should contain some",
            new MappingOfExampleYaml().toString().length(),
            new Satisfies<Integer>(
                input -> {
                    return input > 10;
                }
            )
        ).affirm();
    }

}
