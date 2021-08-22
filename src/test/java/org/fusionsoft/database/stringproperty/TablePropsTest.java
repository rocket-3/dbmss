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
import org.fusionsoft.database.StringProperty;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TablePropsTest {

    @Test
    public void canUse() {
        final String key1 = "param1";
        final String key2 = "param2";
        final String value1 = "ABC";
        final int value2 = 100;

        final MapOfProps map = new MapOfProps(
            new TableProps(value1, value2)
        );

        assertEquals(
            value1,
            map.get(key1).asString()
        );

        assertEquals(
            String.valueOf(value2),
            map.get(key2).asString()
        );
    }

    @Test
    public void canConstructFromProps() {
        final String key1 = "param1";
        final String key2 = "param2";
        final String value1 = "ABC";
        final int value2 = 100;

        Assertions.assertEquals(
            2,
            new TableProps(
                new SetOf<>(
                    new StringProperty.Of("param1", value1),
                    new StringProperty.Of("param2", value2)
                )
            ).size()
        );
    }

    @Test
    public void cantConstructWithMissingProp() {
        final String key1 = "param1";
        final String key2 = "paramX";
        final String value1 = "ABC";
        final int value2 = 100;

        Assertions.assertThrows(
            RuntimeException.class,
            () -> new TableProps(
                new SetOf<>(
                    new StringProperty.Of(key1, value1),
                    new StringProperty.Of(key2, value2)
                )
            ).size()
        );
    }

    @Test
    public void cantConstructWithWrongTypeProp() {
        final String key1 = "param1";
        final String key2 = "param2";
        final String value1 = "ABC";
        final String value2 = "100";

        Assertions.assertThrows(
            RuntimeException.class,
            () -> new TableProps(
                new SetOf<>(
                    new StringProperty.Of(key1, value1),
                    new StringProperty.Of(key2, value2)
                )
            ).size()
        );
    }

}
