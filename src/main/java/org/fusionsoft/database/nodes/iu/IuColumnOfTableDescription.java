package org.fusionsoft.database.nodes.iu;

public interface IuColumnOfTableDescription {
    String iuColumn();
    String dbName();
    String type();
    Boolean nullable();
    String description();
    String dbLocalIdMethod();
}
