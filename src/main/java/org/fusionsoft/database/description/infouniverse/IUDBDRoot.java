package org.fusionsoft.database.description.infouniverse;

public interface IUDBDRoot {
    String type() throws Exception;
    String title();
    String version();
    String summary();
    String description();
    String termsOfService();
    String contact();
    String license();
    IUDBD databaseDescription();
}
