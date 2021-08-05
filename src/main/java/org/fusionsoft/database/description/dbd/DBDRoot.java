package org.fusionsoft.database.description.dbd;

public interface DBDRoot {
    String type() throws Exception;
    String title();
    String version();
    String summary();
    String description();
    String termsOfService();
    String contact();
    String license();
    DBD databaseDescription();
}
