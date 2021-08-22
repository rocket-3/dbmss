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
package org.fusionsoft.database.stringproperty;

import org.cactoos.set.SetOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.StringPropertyType;
import org.fusionsoft.database.stringproperty.signature.SimpleStringPropertySignature;
import org.junit.jupiter.api.Test;

class PropsHasKeysTest {

    @Test
    public void creates() {
        new PropsHasKeys(
            new SetOf<>(
                new SimpleStringPropertySignature("param1", StringPropertyType.Text),
                new SimpleStringPropertySignature("param2", StringPropertyType.Integer)
            ),
            new SetOf<>(
                new StringProperty.Of("param1", "abc"),
                new StringProperty.Of("param2", 2)
            )
        ).forEach(
            x -> {
                System.out.println(new UncheckedText(x).asString());
            }
        );
    }

}
