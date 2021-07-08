package org.fusionsoft.database.description.infouniverse;

import java.io.IOException;

public interface IUServerDBD {
    String key() throws IOException;
    String dbType() throws IOException;
    String url() throws IOException;
    String user() throws IOException;
    String password() throws IOException;
    String description() throws IOException;
    String variables() throws IOException;
}
