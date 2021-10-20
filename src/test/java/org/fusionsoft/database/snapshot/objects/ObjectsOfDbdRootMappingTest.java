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

import org.cactoos.iterable.Filtered;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Unchecked;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.mapping.dbd.DbdRootMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.dbd.ObjectsOfDbdRootMapping;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link ObjectsOfDbdRootMapping} class.
 * @since 0.1
 * @checkstyle MagicNumberCheck (100 lines)
 */
class ObjectsOfDbdRootMappingTest {

    /**
     * Returns four tables of example yaml.
     */
    @Test
    public void getsFourTablesOfExampleYaml() {
        Assertions.assertTrue(
            new Unchecked<>(
                new And(
                    new Filtered<>(
                        o -> o.signature().type().equals(ObjectType.TABLE),
                        new ObjectsOfDbdRootMapping(
                            new DbdRootMapping(
                                new MappingOfExampleYaml()
                            )
                        )
                    ),
                    (Iterable<DbObject<?>> itrb) -> new SetOf<>(itrb).size() == 4,
                    (Iterable<DbObject<?>> itrb) -> new And(
                        item -> !item.asYaml().toString().isEmpty(),
                        itrb
                    ).value()
                )
            ).value()
        );
    }

}
