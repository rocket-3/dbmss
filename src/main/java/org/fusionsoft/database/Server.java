/*
 * Copyright (C) 2018-2021 FusionSoft
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 *
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.fusionsoft.database;

import java.sql.Connection;
import org.fusionsoft.lib.connection.NotImplementedConnection;

/**
 * The interface Server representing a database in 'servers' section in DBGit.
 * @since 0.1
 */
public interface Server {

    /**
     * The constant FAKE.
     */
    Server FAKE = new Server() {
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
            return DbmsSignature.ABSENT;
        }
    };

    /**
     * Connection by JDBC.
     * @return The java.sql.Connection.
     */
    Connection connection();

    /**
     * Name of server in the list.
     * @return The string.
     */
    String name();

    /**
     * The signature of DBMS.
     * @return The DbmsSignature.
     */
    DbmsSignature dbmsSignature();

}
