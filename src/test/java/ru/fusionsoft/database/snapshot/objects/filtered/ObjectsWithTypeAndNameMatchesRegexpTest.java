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

import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Or;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;
import ru.fusionsoft.database.mapping.MappingOfExampleYaml;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdRootMapping;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeData;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;

/**
 * The tests for {@link ObjectsWithTypeAndNameMatchesRegexp} class.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (1000 lines)
 */
@SuppressWarnings("PMD")
class ObjectsWithTypeAndNameMatchesRegexpTest {

    /**
     * Matches expected types.
     * @throws Exception When can't.
     */
    @Test
    public void matchesExpectedTypes() throws Exception {
        new Assertion<>(
            "Should contain only indexes and constraints objects",
            new And(
                new ObjectsWithTypeAndNameMatchesRegexp<>(
                    new TextOf("index|constraint"),
                    new TextOf(".*"),
                    new ObjectsOfDbdRootMapping(
                        new DbdRootMapping(
                            new MappingOfExampleYaml()
                        )
                    )
                ),
                objects -> new ListOf<>(objects).size() > 0,
                objects -> new And(
                    new Mapped<>(
                        object -> new Or(
                            () -> object.signature().type().equalsTo(new ObjectTypeIndex()),
                            () -> object.signature().type().equalsTo(new ObjectTypeConstraint())
                        ),
                        objects
                    )
                ).value()
            ).value(),
            new IsTrue()
        ).affirm();
    }

    /**
     * Matches expected names and type.
     * @throws Exception When can't.
     */
    @Test
    public void matchesExpectedNamesAndType() throws Exception {
        new Assertion<>(
            "Should contain only indexes and constraints objects",
            new And(
                new ObjectsWithTypeAndNameMatchesRegexp<>(
                    new TextOf("data"),
                    new TextOf("mts\\.domain.*"),
                    new ObjectsOfDbdRootMapping(
                        new DbdRootMapping(
                            new MappingOfExampleYaml()
                        )
                    )
                ),
                objects -> new ListOf<>(objects).size() > 0,
                objects -> new And(
                    new Mapped<>(
                        object -> new And(
                            () -> object.signature().type().equalsTo(new ObjectTypeData()),
                            () -> object.signature().name().asString().contains("domain")
                        ),
                        objects
                    )
                ).value()
            ).value(),
            new IsTrue()
        ).affirm();
    }

}
