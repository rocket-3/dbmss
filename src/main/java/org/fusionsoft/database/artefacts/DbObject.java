package org.fusionsoft.database.artefacts;

import java.util.Collection;
import java.util.Collections;

public interface DbObject {
    DbObjectSignature signature();
    Collection<StringProperty> props();
    
    DbObject Absent = new DbObject() {
        @Override
        public DbObjectSignature signature() {
            return DbObjectSignature.Absent;
        }

        @Override
        public Collection<StringProperty> props() {
            return Collections.emptyList();
        }
    };
}
