package org.fusionsoft.database;

public interface DbObjectSignature {
    String name();
    String parentName();
    DbObjectType type();
    DbmsSignature dbmsSignature();
    
    DbObjectSignature Absent = new DbObjectSignature() {

        @Override
        public String name() {
            return "";
        }

        @Override
        public String parentName() {
            return "";
        }

        @Override
        public DbObjectType type() {
            return DbObjectType.Absent;
        }

        @Override
        public DbmsSignature dbmsSignature() {
            return DbmsSignature.Absent;
        }
    };
}
