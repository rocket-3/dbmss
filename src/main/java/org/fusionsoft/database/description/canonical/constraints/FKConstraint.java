package org.fusionsoft.database.description.canonical.constraints;

public interface FKConstraint {
    String sourceTableName();
    String sourceColumnName();
    String targetTableName();
    String targetColumnName();
}
