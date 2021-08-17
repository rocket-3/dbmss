package org.fusionsoft.database;

public interface IS<DescriptionType> {
    Migration restore() throws Exception;
    DescriptionType describe() throws Exception;
}
