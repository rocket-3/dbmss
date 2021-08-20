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
