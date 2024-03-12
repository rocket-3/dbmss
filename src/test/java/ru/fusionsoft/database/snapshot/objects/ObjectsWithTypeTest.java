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

import org.cactoos.list.ListOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.mapping.MappingOfExampleYaml;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The tests for {@link ObjectsWithTypeCasted}.
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
                new ObjectsWithTypeCasted<>(
                    new ObjectTypeTable(),
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
