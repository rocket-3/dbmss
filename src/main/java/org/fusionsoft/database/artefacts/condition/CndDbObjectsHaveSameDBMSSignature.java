package org.fusionsoft.database.artefacts.condition;

import java.util.Collection;
import org.cactoos.Scalar;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import org.fusionsoft.database.artefacts.Condition;
import org.fusionsoft.database.artefacts.DbObject;
import org.fusionsoft.database.artefacts.DbmsSignature;

public class CndDbObjectsHaveSameDBMSSignature implements Condition {

    private final Scalar<Boolean> booleanScalar;

    public CndDbObjectsHaveSameDBMSSignature(DbmsSignature dbmsSignature, Collection<DbObject> dbObjectCollection) {
        this.booleanScalar = new And(
            new Mapped<>(
                x -> new CndDbObjectHaveSameDBMSSignature(dbmsSignature, x),
                dbObjectCollection
            )
        );
    }

    @Override
    public Boolean value() throws Exception {
        return booleanScalar.value();
    }
}
