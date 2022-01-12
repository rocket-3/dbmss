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

import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameDelimiter;

/**
 * The tests for {@link SimpleObjectName}.
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class FullObjectNameTest {

    /**
     * Represents single word from single argument.
     */
    @Test
    public void singleWordFromSingle() {
        final String name = "name";
        Assertions.assertEquals(
            name,
            new SimpleObjectName(name).asString()
        );
    }

    /**
     * Represents delimited words from several arguments.
     * @throws Exception When can't.
     */
    @Test
    public void delimitedWordsFromSeveral() throws Exception {
        final String parent = "parent";
        final String child = "child";
        Assertions.assertEquals(
            MessageFormat.format(
                "{0}{1}{2}",
                parent,
                new SimpleObjectNameDelimiter().asString(),
                child
            ),
            new SimpleObjectName(parent, child).asString()
        );
    }

    /**
     * Represents delimited words from several arguments.
     */
    @Test
    public void doubleNamesWorks() {
        final Text parent = new TextOf("parent");
        final Text child = new TextOf("child");
        Assertions.assertEquals(
            new SimpleObjectName(
                parent,
                child,
                child,
                parent
            ).asString(),
            new SimpleObjectName(
                parent,
                child,
                new SimpleObjectName(child, parent)
            ).asString()
        );
    }

}
