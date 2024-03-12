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
package ru.fusionsoft.database.snapshot.objects.filtered;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.And;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfMapping;
import ru.fusionsoft.database.mapping.MappingOfExampleYaml;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.yaml.MappingEmpty;

/**
 * The tests for {@link ObjectsWithDataMentionedInDbdFile}.
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
        final Func<String, DbObject<YamlNode>> mktable = name -> new SimpleDbObject<>(
            new MappingEmpty(),
            new SimpleObjectSignature(
                new SimpleObjectName("mts", name),
                new ObjectTypeTable()
            )
        );
        final Iterable<DbObject<DbdTableMapping>> filtered = new ObjectsWithDataMentionedInDbdFile(
            new IterableOf<>(
                mktable.apply(datatable),
                mktable.apply("vendors")
            ),
            new DbdReadableOfMapping(new MappingOfExampleYaml())
        );
        Assertions.assertTrue(
            new And(
                () -> new ListOf<>(filtered).size() == 1,
                () -> filtered.iterator().next().signature().asString().equals(
                    mktable.apply(datatable).signature().asString()
                )
            ).value()
        );
    }

}
