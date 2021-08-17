package org.fusionsoft.database;

import java.sql.Connection;
import org.fusionsoft.lib.connection.NotImplementedConnection;

public interface Server {
    Connection connection();
    String name();
    DbmsSignature dbmsSignature();
    
    Server Fake = new Server() {
        @Override
        public Connection connection() {
            return new NotImplementedConnection();
        }

        @Override
        public String name() {
            return "Fake server for tests";
        }

        @Override
        public DbmsSignature dbmsSignature() {
            return DbmsSignature.Absent;
        }
    };
}
