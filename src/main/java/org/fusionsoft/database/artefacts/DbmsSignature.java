package org.fusionsoft.database.artefacts;

public interface DbmsSignature {
    DbmsName dbmsName();
    String dbmsVersion();
    
    DbmsSignature Absent = new DbmsSignature() {
        @Override
        public DbmsName dbmsName() {
            return DbmsName.Absent;
        }

        @Override
        public String dbmsVersion() {
            return "";
        }
    };
}
