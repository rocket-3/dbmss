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

import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.ParentName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link ParentName}.
 * @since 0.1
 */
class ParentNameTest {

    /**
     * Represents expected name of parent.
     * @throws Exception When can't.
     */
    @Test
    public void works() throws Exception {
        Assertions.assertEquals(
            "c",
            new ParentName(
                new FullObjectName("a", "b", "c", "d")
            ).asString()
        );
    }

}
