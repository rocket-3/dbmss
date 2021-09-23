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

import com.amihaiemil.eoyaml.YamlMapping;
import java.sql.Connection;
import org.fusionsoft.database.BaseYamlRepresentative;
import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.dbd.document.DbdServerYamlMapping;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.lib.connection.NotImplementedConnection;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;

/**
 * The type of {@link DatabaseInfo} that is obtained from {@link DbdFile} and
 *  name of server for which data is.
 * @since 0.1
 * @todo #45:30min Implement methods and create a unit test.
 */
@SuppressWarnings("PMD")
public class DatabaseInfoOfDbd extends BaseYamlRepresentative implements DatabaseInfo {

    /**
     * The YamlMapping encapsulated.
     */
    private final DbdServerYamlMapping mapping;

    /**
     * The String encapsulated.
     */
    private final String name;

    /**
     * Instantiates a new Database info of dbd.
     * @param file The DbdFile to be encapsulated.
     * @param name The String to be encapsulated.
     */
    public DatabaseInfoOfDbd(final DbdFile file, final String name) {
        this(
            new YamlMappingOfPath(
                file.asYaml(),
                "servers", name
            ),
            name
        );
    }

    /**
     * Instantiates a new Database info of dbd.
     * @param mapping The YamlMapping, data should be contained in.
     * @param name The name to be encapsulated.
     */
    private DatabaseInfoOfDbd(final DbdServerYamlMapping mapping, final String name) {
        super(mapping);
        this.mapping = mapping;
        this.name = name;
    }

    /**
     * Instantiates a new Database info of dbd.
     * @param mapping The DbdServerYamlMapping, data should be contained in.
     * @param name The name to be encapsulated.
     */
    private DatabaseInfoOfDbd(final YamlMapping mapping, final String name) {
        this(new DbdServerYamlMapping(mapping), name);
    }

    @Override
    public final DbmsSignature signature() {
        return new DbmsSignatureOfServerMapping(
            new DbdServerYamlMapping(this.mapping)
        );
    }

    @Override
    public final Connection connection() {
        return new NotImplementedConnection();
    }

    @Override
    public final String name() {
        return this.name;
    }

}