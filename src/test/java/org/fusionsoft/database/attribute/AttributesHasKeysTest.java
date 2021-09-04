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
package org.fusionsoft.database.attribute;

import org.cactoos.set.SetOf;
import org.fusionsoft.database.Attribute;
import org.fusionsoft.database.attribute.signature.SimpleAttributeSignature;
import org.fusionsoft.database.attribute.type.TypeInteger;
import org.fusionsoft.database.attribute.type.TypeText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The type {@link AttributesHasKeys} test.
 * @since 0.1
 */
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.IntegerInstantiation"})
class AttributesHasKeysTest {

    /**
     * It works with proper props without an error.
     */
    @Test
    public void passesWithRightProps() {
        new AttributesHasKeys(
            new SetOf<>(
                new SimpleAttributeSignature(
                    "param1",
                    new TypeText()
                ),
                new SimpleAttributeSignature(
                    "param2",
                    new TypeInteger()
                )
            ),
            new SetOf<>(
                new AttributeOf("param1", "abc"),
                new AttributeOf("param2", 2)
            )
        ).forEach(
            Attribute::value
        );
    }

    /**
     * With wrong props it throws {@link RuntimeException}.
     */
    @Test
    public void failsWithWrongProps() {
        Assertions.assertThrows(
            RuntimeException.class,
            () -> new AttributesHasKeys(
                new SetOf<>(
                    new SimpleAttributeSignature(
                        "param1",
                        new TypeInteger()
                    )
                ),
                new SetOf<>(
                    new AttributeOf("param1", 1.0)
                )
            ).forEach(
                Attribute::value
            )
        );
    }

}
