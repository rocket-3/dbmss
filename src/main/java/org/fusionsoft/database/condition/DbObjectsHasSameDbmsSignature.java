package org.fusionsoft.database.condition;

import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;

public class DbObjectsHasSameDbmsSignature extends ConditionEnvelope {
    public DbObjectsHasSameDbmsSignature(DbObject first, DbObject second) {
        super(
            () -> {
                return first.signature().dbmsSignature().equals(
                    second.signature().dbmsSignature()   
                );
            }
        );
    }
    
    public DbObjectsHasSameDbmsSignature(DiffPair<DbObject> pair) {
        super(
            () -> {
                return pair.currentValue().signature().dbmsSignature().equals(
                    pair.previousValue().signature().dbmsSignature()   
                );
            }
        );
    }
}
