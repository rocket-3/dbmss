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
package org.fusionsoft.database.mapping.dbd;

import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.DbdFileOfMapping;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.mapping.fields.DbdRootFields;
import org.fusionsoft.database.snapshot.databaseinfo.DatabaseInfoOfDbd;
import org.fusionsoft.database.snapshot.objects.dbd.ObjectsOfDbdRootMapping;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link DbdRootMappingBuilt}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbdRootMappingBuiltTest {

    /**
     * Creates a mapping identical to input in text representation.
     */
    @Test
    public void createsMappingIdenticalToInput() {
        final MappingOfExampleYaml yaml = new MappingOfExampleYaml();
        final DbdFileOfMapping file = new DbdFileOfMapping(yaml);
        Assertions.assertEquals(
            yaml.toString(),
            new DbdRootMappingBuilt(
                new IterableOf<>(
                    new DatabaseInfoOfDbd(
                        file,
                        "fs-mts"
                    )
                ),
                new DbdInfoMapping(
                    new YamlMappingOfPath(
                        yaml,
                        DbdRootFields.INFO
                    )
                ),
                new ObjectsOfDbdRootMapping(
                    file.asYaml()
                )
            ).toString()
        );
    }

}
