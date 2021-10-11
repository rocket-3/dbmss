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
package org.fusionsoft.database.snapshot.databaseinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.mapping.dbd.DbdServerMapping;
import org.fusionsoft.database.mapping.dbd.DbdServerMappingOfDbdFile;
import org.fusionsoft.database.mapping.fields.DbdServerFields;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.database.snapshot.DbmsSignature;
import org.fusionsoft.database.snapshot.dbmssignature.DbmsSignatureOfServerMapping;
import org.fusionsoft.lib.connection.ConnectionOfScalar;

/**
 * The type of {@link DatabaseInfo} that is obtained from {@link DbdFile} and
 *  name of server for which data is.
 * @since 0.1
 */
public class DatabaseInfoOfDbd implements DatabaseInfo {

    /**
     * The YamlMapping encapsulated.
     */
    private final DbdServerMapping mapping;

    /**
     * The String of database name in terms of 'DBD' format encapsulated.
     */
    private final String key;

    /**
     * Instantiates a new Database info of dbd.
     * @param file The DbdFile to be encapsulated.
     * @param name The String to be encapsulated.
     */
    public DatabaseInfoOfDbd(final DbdFile file, final String name) {
        this(
            new DbdServerMappingOfDbdFile(file, name),
            name
        );
    }

    /**
     * Instantiates a new Database info of dbd.
     * @param mapping The YamlMapping, data should be contained in.
     * @param name The name to be encapsulated.
     */
    private DatabaseInfoOfDbd(final DbdServerMapping mapping, final String name) {
        this.mapping = mapping;
        this.key = name;
    }

    @Override
    public final DbmsSignature signature() {
        return new DbmsSignatureOfServerMapping(
            new DbdServerMapping(this.mapping)
        );
    }

    @Override
    public final Connection connection() {
        return new ConnectionOfScalar(
            () -> DriverManager.getConnection(
                this.mapping.string(DbdServerFields.URL.asString()),
                this.mapping.string(DbdServerFields.USER.asString()),
                this.mapping.string(DbdServerFields.PWD.asString())
            )
        );
    }

    @Override
    public final String name() {
        return this.key;
    }

    @Override
    public final DbdServerMapping asYaml() {
        return this.mapping;
    }

}
