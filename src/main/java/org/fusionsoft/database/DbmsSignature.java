package org.fusionsoft.database;

public interface DbmsSignature {
    DbmsName dbmsName();
    String dbmsVersion();
    
    public DbmsSignature Absent = new DbmsSignature() {
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
