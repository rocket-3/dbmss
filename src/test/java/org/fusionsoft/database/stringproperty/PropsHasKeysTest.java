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
package org.fusionsoft.database.stringproperty;

import org.cactoos.set.SetOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;
import org.fusionsoft.database.stringproperty.type.Chars;
import org.fusionsoft.database.stringproperty.type.Int;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type {@link PropsHasKeys} test.
 * @since 0.1
 */
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.IntegerInstantiation"})
class PropsHasKeysTest {

    /**
     * It works with proper props without an error.
     */
    @Test
    public void passesWithRightProps() {
        new PropsHasKeys(
            new SetOf<>(
                new SimpleStringPropertySignature(
                    "param1",
                    new Chars()
                ),
                new SimpleStringPropertySignature(
                    "param2",
                    new Int()
                )
            ),
            new SetOf<>(
                new StringProperty.Of("param1", "abc"),
                new StringProperty.Of("param2", 2)
            )
        ).forEach(
            x -> new UncheckedText(x).asString()
        );
    }

    /**
     * With wrong props it throws {@link RuntimeException}.
     */
    @Test
    public void failsWithWrongProps() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> new PropsHasKeys(
                new SetOf<>(
                    new SimpleStringPropertySignature(
                        "param1",
                        new Int()
                    )
                ),
                new SetOf<>(
                    new StringProperty.Of("param1", 1.0)
                )
            ).forEach(
                x -> new UncheckedText(x).asString()
            )
        );
    }

}
