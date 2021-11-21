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
package org.fusionsoft.database.snapshot.objects.filtered;

import org.cactoos.Func;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.And;
import org.fusionsoft.database.dbdfile.DbdFileOfMapping;
import org.fusionsoft.database.mapping.MappingOfExampleYaml;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.ObjectsEnvelope;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectNameOfValues;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.yaml.MappingEmpty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link ObjectsAreDataTablesInDbd}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class ObjectsMentionedInDbdAsDataTableTest {

    /**
     * Retrieves expected tables of example yaml.
     * @throws Exception When can't.
     */
    @Test
    public void retrievesExpectedTablesOfExampleYaml() throws Exception {
        final String datatable = "domains";
        final Func<String, DbObject<?>> object = name -> new SimpleDbObject<>(
            new MappingEmpty(),
            new SimpleObjectSignature(
                new SimpleObjectNameOfValues("mts", name),
                ObjectType.TABLE
            )
        );
        final Objects filtered = new ObjectsAreDataTablesInDbd(
            new ObjectsEnvelope(
                new IterableOf<>(
                    object.apply(datatable),
                    object.apply("vendors")
                )
            ),
            new DbdFileOfMapping(new MappingOfExampleYaml())
        );
        Assertions.assertTrue(
            new And(
                () -> new ListOf<>(filtered).size() == 1,
                () -> filtered.iterator().next().signature().asString().equals(
                    object.apply(datatable).signature().asString()
                )
            ).value()
        );
    }

}
