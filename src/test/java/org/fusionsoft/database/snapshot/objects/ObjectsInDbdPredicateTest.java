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

import org.fusionsoft.database.dbdfile.DbdFileOfMapping;
import org.fusionsoft.database.mapping.MappingEmpty;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.snapshot.ObjectType;
import org.fusionsoft.database.snapshot.SimpleDbObject;
import org.fusionsoft.database.snapshot.objects.predicate.ObjectMentionedInDbdFilePredicate;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link ObjectMentionedInDbdFilePredicate}.
 * @since 0.1
 */
class ObjectsInDbdPredicateTest {

    /**
     * Matches object of example yaml.
     */
    @Test
    public void matchesObject() {
        Assertions.assertTrue(
            new ObjectMentionedInDbdFilePredicate(
                new DbdFileOfMapping(
                    new MappingOfExampleYaml()
                )
            ).apply(
                new SimpleDbObject<>(
                    new MappingEmpty(),
                    new SimpleObjectSignature(
                        new FullObjectName(
                            "mts",
                            "domains",
                            "pk_domains"
                        ),
                        ObjectType.CONSTRAINT
                    )
                )
            )
        );
    }

}
