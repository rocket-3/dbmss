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
package org.fusionsoft.database.dbobject;

import org.cactoos.scalar.And;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.DbObjectType;
import org.fusionsoft.database.DbmsVersion;
import org.fusionsoft.database.attribute.AttributeOf;
import org.fusionsoft.database.attribute.NameMapOfProps;
import org.fusionsoft.database.dbms.signature.PgDbmsSignature;
import org.fusionsoft.database.dbobject.signature.SimpleDbObjectSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test of SimpleDbObjectTest.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class SimpleDbObjectTest {

    /**
     * Has data.
     * @throws Exception When can't.
     */
    @Test
    void hasData() throws Exception {
        final String name = "rental";
        final String schema = "public";
        final String partitioned = "isPartitioned";
        final boolean bool = false;
        final String rows = "rows";
        final int number = 100;
        Assertions.assertTrue(
            new And(
                new SimpleDbObject(
                    new SimpleDbObjectSignature(
                        new TextOf(name),
                        new TextOf(schema),
                        DbObjectType.TABLE,
                        new PgDbmsSignature(
                            DbmsVersion.DUMMY
                        )
                    ),
                    new SetOf<>(
                        new AttributeOf(partitioned, bool),
                        new AttributeOf(rows, number)
                    )
                ),
                x -> x.signature().name().equals(name),
                x -> new NameMapOfProps(x.attributes())
                    .get(rows)
                    .value()
                    .equals(String.valueOf(number)),
                x -> new NameMapOfProps(x.attributes())
                    .get(partitioned)
                    .value()
                    .equals(String.valueOf(bool))
            ).value()
        );
    }

}
