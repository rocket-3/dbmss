package org.fusionsoft.database.nodes.iu;

public interface IuDocDescription {
    String type() throws Exception;
    String title();
    String version();
    String summary();
    String description();
    String termsOfService();
    String contact();
    String license();
    IuDatabaseDescription databaseDescription();
}
