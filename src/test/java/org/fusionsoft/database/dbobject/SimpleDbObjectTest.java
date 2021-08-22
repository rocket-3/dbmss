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
 *
 */
package org.fusionsoft.database.dbobject;

import org.cactoos.scalar.And;
import org.cactoos.set.SetOf;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.DbObjectType;
import org.fusionsoft.database.DbmsVersion;
import org.fusionsoft.database.StringProperty;
import org.fusionsoft.database.dbms.signature.PgDbmsSignature;
import org.fusionsoft.database.dbobject.signature.SimpleDbObjectSignature;
import org.fusionsoft.database.stringproperty.MapOfProps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleDbObjectTest {

    @Test
    void hasData() throws Exception {
        final String name = "rental";
        final String schema = "public";
        final String key1 = "isPartitioned";
        final boolean value1 = false;
        final String key2 = "rows";
        final int value2 = 100;
        Assertions.assertTrue(
            new And(
                new SimpleDbObject(
                    new SimpleDbObjectSignature(
                        new TextOf(name),
                        new TextOf(schema),
                        DbObjectType.Table,
                        new PgDbmsSignature(
                            DbmsVersion.Dummy
                        )
                    ),
                    new SetOf<>(
                        new StringProperty.Of(key1, value1),
                        new StringProperty.Of(key2, value2)
                    )
                ),
                x -> x.signature().name().equals(name),
                x -> new MapOfProps(x.props())
                    .get(key2)
                    .asString()
                    .equals(String.valueOf(value2)),
                x -> new MapOfProps(x.props())
                    .get(key1)
                    .asString()
                    .equals(String.valueOf(value1))
            ).value()
        );
    }

}
