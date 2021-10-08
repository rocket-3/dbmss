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

import org.cactoos.text.TextOf;
import org.fusionsoft.database.DbdFileOfMapping;
import org.fusionsoft.database.mapping.MappingEmpty;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.snapshot.NaiveDbObject;
import org.fusionsoft.database.snapshot.ObjectType;
import org.fusionsoft.database.snapshot.objectsignature.NaiveObjectSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link ObjectsInDbdPredicate}.
 * @since 0.1
 */
class ObjectsInDbdPredicateTest {

    /**
     * Matches object of example yaml.
     */
    @Test
    public void matchesObject() {
        Assertions.assertTrue(
            new ObjectsInDbdPredicate(
                new DbdFileOfMapping(
                    new MappingOfExampleYaml()
                )
            ).apply(
                new NaiveDbObject(
                    new MappingEmpty(),
                    new NaiveObjectSignature(
                        new NamesJoined(
                            new TextOf("mts"),
                            new TextOf("domains"),
                            new TextOf("pk_domains")
                        ),
                        ObjectType.CONSTRAINT
                    )
                )
            )
        );
    }

}
