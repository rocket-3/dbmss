package org.fusionsoft.database.artefacts;

public interface IS<DescriptionType> {
    Migration restore() throws Exception;
    DescriptionType describe() throws Exception;
}
