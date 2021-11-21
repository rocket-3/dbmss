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
package org.fusionsoft.database.snapshot.objects;

import java.text.MessageFormat;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectNameDelimiter;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectNameOfValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link SimpleObjectNameOfValues}.
 * @since 0.1
 */
class FullObjectNameTest {

    /**
     * Represents single word from single argument.
     * @throws Exception When can't.
     */
    @Test
    public void singleWordFromSingle() throws Exception {
        final String name = "name";
        Assertions.assertEquals(
            name,
            new SimpleObjectNameOfValues(name).asString()
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
                "{0}{1}{2}", parent, new SimpleObjectNameDelimiter().asString(), child
            ),
            new SimpleObjectNameOfValues(parent, child).asString()
        );
    }

}
