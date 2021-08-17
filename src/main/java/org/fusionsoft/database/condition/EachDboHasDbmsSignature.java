package org.fusionsoft.database.condition;

import java.util.Collection;
import org.cactoos.Scalar;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import org.fusionsoft.database.Condition;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbmsSignature;

public class EachDboHasDbmsSignature extends ConditionEnvelope {
    public EachDboHasDbmsSignature(DbmsSignature dbmsSignature, Collection<DbObject> dbObjectCollection) {
        super( 
            new And(
                new Mapped<>(
                    x -> new DboHasDbmsSignature(dbmsSignature, x),
                    dbObjectCollection
                )
            )
        );
    }
}
