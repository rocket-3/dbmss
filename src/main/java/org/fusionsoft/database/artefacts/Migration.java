package org.fusionsoft.database.artefacts;

public interface Migration {
    boolean validate() throws Exception;
    void perform() throws Exception;
}
