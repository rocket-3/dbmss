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

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSchema;
import ru.fusionsoft.lib.yaml.MappingEmpty;

/**
 * The test for {@link ObjectsWithParent}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class ObjectsWithParentTest {

    /**
     * Works.
     * @throws Exception When can't.
     */
    @Test
    public void works() throws Exception {
        final int size = 3;
        final String schema = "public";
        final Func<Iterable<String>, DbObject<YamlMapping>> func = iterable -> new SimpleDbObject<>(
            new MappingEmpty(),
            new SimpleObjectSignature(
                new SimpleObjectName(
                    new Mapped<Text>(
                        TextOf::new,
                        iterable
                    )
                ),
                new ObjectTypeSchema()
            )
        );
        new Assertion<>(
            "Should have 3 children",
            new SetOf<>(
                new ObjectsWithParent<>(
                    func.apply(
                        new IterableOf<String>(schema)
                    ),
                    new IterableOf<>(
                        func.apply(new IterableOf<>(schema, "1")),
                        func.apply(new IterableOf<>(schema, "2")),
                        func.apply(new IterableOf<>(schema, "3"))
                    )
                )
            ),
            new HasSize(size)
        );
    }

}
