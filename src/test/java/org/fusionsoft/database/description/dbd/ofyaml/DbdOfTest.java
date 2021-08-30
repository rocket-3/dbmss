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
package org.fusionsoft.database.description.dbd.ofyaml;

import java.io.IOException;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.text.TextOfExampleYaml;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.yaml.YamlInputOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link DbdOf} behaviour.
 * @since 0.1
 */
class DbdOfTest {

    /**
     * Constructs from {@link TextOfExampleYaml} and can return
     *  4 nested {@link org.fusionsoft.database.description.dbd.Table}
     *  without exception.
     * @throws IOException When can't.
     * @checkstyle MagicNumberCheck (100 lines)
     */
    @Test
    public void constructsFromText() throws IOException {
        Assertions.assertEquals(
            4,
            new SetOf<>(
                new DbdOf(
                    new DBDYamlInput(
                        new YamlInputOf(
                            new TextOfExampleYaml()
                        )
                    )
                )
                    .schemaDescriptions()
                    .iterator()
                    .next()
                    .tableDescriptions()
            ).size()
        );
    }

}
