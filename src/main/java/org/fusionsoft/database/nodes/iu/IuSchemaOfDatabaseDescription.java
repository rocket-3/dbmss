package org.fusionsoft.database.nodes.iu;

public interface IuSchemaOfDatabaseDescription {
    String key();
    String owner();
    Iterable<IuTableOfSchemaDescription> tableDescriptions();
}
