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

import org.cactoos.iterable.Filtered;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValuesMatching;
import ru.fusionsoft.database.mapping.MappingOfExampleYaml;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The test for {@link ObjectsOfDbdRootMapping} class.
 * @since 0.1
 * @checkstyle MagicNumberCheck (100 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class ObjectsOfDbdRootMappingTest {

    /**
     * Returns four tables of example yaml.
     */
    @Test
    public void getsFourTablesOfExampleYaml() {
        new Assertion<>(
            "Should get 4 tables of example DBD yaml",
            new Filtered<>(
                o -> o.signature().type().equals(new ObjectTypeTable()),
                new ObjectsOfDbdRootMapping(
                    new DbdRootMapping(
                        new MappingOfExampleYaml()
                    )
                )
            ),
            new HasSize(4)
        ).affirm();
    }

    /**
     * Returns four tables of example yaml.
     */
    @Test
    public void getsCorrectTablesOfExampleYaml() {
        new Assertion<>(
            "Should get non-empty tables of example DBD yaml",
            new Filtered<>(
                o -> o.signature().type().equals(new ObjectTypeTable()),
                new ObjectsOfDbdRootMapping(
                    new DbdRootMapping(
                        new MappingOfExampleYaml()
                    )
                )
            ),
            new HasValuesMatching<>(
                val -> !val.asYaml().toString().isEmpty()
            )
        ).affirm();
    }

}
