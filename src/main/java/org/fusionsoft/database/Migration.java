package org.fusionsoft.database;

public interface Migration {
    boolean validate() throws Exception;
    void perform() throws Exception;
}
