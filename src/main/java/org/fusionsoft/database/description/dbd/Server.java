package org.fusionsoft.database.description.dbd;

import java.io.IOException;

public interface Server {
    String key() throws IOException;
    String dbType() throws IOException;
    String url() throws IOException;
    String user() throws IOException;
    String password() throws IOException;
    String description() throws IOException;
    String variables() throws IOException;
}
