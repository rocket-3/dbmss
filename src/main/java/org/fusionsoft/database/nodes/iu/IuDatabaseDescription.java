package org.fusionsoft.database.nodes.iu;

public interface IuDatabaseDescription {
    Iterable<IuServerOfDatabaseDescription> serverDescriptions();
    Iterable<IuSchemaOfDatabaseDescription> schemaDescriptions();
}
