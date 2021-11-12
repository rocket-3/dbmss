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

import org.cactoos.list.ListOf;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.mapping.dbd.DbdRootMapping;
import org.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdRootMapping;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link ObjectsWithType}.
 * @since 0.1
 */
class ObjectsWithTypeTest {

    /**
     * Retrieves all tables of example yaml.
     */
    @Test
    public void retrievesAllTablesOfExampleYaml() {
        final int tables = 4;
        Assertions.assertEquals(
            tables,
            new ListOf<>(
                new ObjectsWithType(
                    ObjectType.TABLE,
                    new ObjectsOfDbdRootMapping(
                        new DbdRootMapping(
                            new MappingOfExampleYaml()
                        )
                    )
                )
            ).size()
        );
    }

}
