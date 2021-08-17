package org.fusionsoft.database.migration;

import org.cactoos.Proc;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.And;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.condition.DbObjectsHasSameDbmsSignature;
import org.fusionsoft.database.condition.DboHasDbmsSignature;

//generates dbms/object type specific migration
public class DboSqlMigration extends SqlMigration {

    public DboSqlMigration(DiffPair<DbObject> diffPair, RestoreParams restoreParams, Proc<Text> consumer) {
        super(
            new TextOf(""),
            consumer,
            new And(
                () -> true,
                new DbObjectsHasSameDbmsSignature(diffPair) 
            )
        );
    }

}
