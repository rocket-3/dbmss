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
package org.fusionsoft.database.snapshot;

import java.sql.Connection;
import org.fusionsoft.database.YamlRepresentative;
import org.fusionsoft.database.snapshot.databaseinfo.DbmsSignature;

/**
 * The type representing information about database,
 *  such as url, kind, version and credentials.
 * @since 0.1
 */
public interface DatabaseInfo extends YamlRepresentative {

    /**
     * Signature of DBMS.
     * @return The dbms signature.
     */
    DbmsSignature signature();

    /**
     * Connection to connect to datavase.
     * @return The {@link java.sql.Connection}.
     */
    Connection connection();

    /**
     * The name of database in terms of 'DBD' file content format.
     * @return The string.
     */
    String name();

}
