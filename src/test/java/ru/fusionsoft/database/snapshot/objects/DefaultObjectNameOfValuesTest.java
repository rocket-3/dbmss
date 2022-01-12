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
package ru.fusionsoft.database.snapshot.objects;

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;

/**
 * The test for {@link SimpleObjectName}.
 * @since 0.1
 */
class DefaultObjectNameOfValuesTest {

    /**
     * Represents expected name of parent.
     * @throws Exception When can't.
     */
    @Test
    public void works() throws Exception {
        final Iterable<Text> parent = new Mapped<>(
            TextOf::new,
            new IterableOf<>("a", "b", "c")
        );
        Assertions.assertEquals(
            new SimpleObjectName(parent).asString(),
            new SimpleObjectName(
                new Joined<Text>(
                    parent,
                    new IterableOf<>(new TextOf("d"))
                )
            )
            .parent()
            .asString()
        );
    }

}
