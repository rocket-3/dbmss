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
package ru.fusionsoft.database.mapping.dbd.built;

import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.dbdfile.DbdFileOfMapping;
import ru.fusionsoft.database.mapping.MappingOfExampleYaml;
import ru.fusionsoft.database.mapping.dbd.DbdInfoMapping;
import ru.fusionsoft.database.mapping.dbd.DbdServersMapping;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerEntryOfDbdFile;
import ru.fusionsoft.database.mapping.fields.DbdRootFields;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdFile;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;

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
                new DbdServersMapping(
                    new DbdServerEntryOfDbdFile(
                        file,
                        new TextOf("fs-mts")
                    )
                ),
                new DbdInfoMapping(
                    new YamlMappingOfPath(
                        yaml,
                        DbdRootFields.INFO
                    )
                ),
                new ObjectsOfDbdFile(file)
            ).toString()
        );
    }

}
