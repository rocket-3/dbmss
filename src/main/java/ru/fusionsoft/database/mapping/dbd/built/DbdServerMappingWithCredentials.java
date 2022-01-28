/*
 * Copyright (C) 2018-2022 FusionSoft
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
package ru.fusionsoft.database.mapping.dbd.built;

import java.sql.Connection;
import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdServerFields;
import ru.fusionsoft.lib.ci.credentials.Credentials;
import ru.fusionsoft.lib.connection.ConnectionOfTextArgs;
import ru.fusionsoft.lib.yaml.MappingWithEntries;
import ru.fusionsoft.lib.yaml.MappingWithKeys;

/**
 * The type of {@link DbdServerMapping} with url and {@link Credentials} values merged into.
 * @since 0.1
 */
public class DbdServerMappingWithCredentials extends DbdServerMapping {

    /**
     * Instantiates a new Dbd server mapping with credentials.
     * @param mapping The DbdServerMapping to merge into.
     * @param connection The {@link Connection} to be encapsulated.
     * @param url The Text to be encapsulated.
     * @param user The Text to be encapsulated.
     * @param password The Text to be encapsulated.
     * @checkstyle ParameterNumberCheck (20 lines)
     */
    private DbdServerMappingWithCredentials(
        final DbdServerMapping mapping,
        final Connection connection,
        final Text url,
        final Text user,
        final Text password
    ) {
        super(
            new MappingWithEntries(
                mapping,
                new ScalarEntry(
                    DbdServerFields.URL, url
                ),
                new ScalarEntry(
                    DbdServerFields.USER, user
                ),
                new ScalarEntry(
                    DbdServerFields.PWD, password
                ),
                new ScalarEntry(
                    DbdServerFields.DBTYPE,
                    () -> connection.getMetaData().getDatabaseProductName()
                ),
                new ScalarEntry(
                    DbdServerFields.DBVERSION,
                    () -> MessageFormat.format(
                        "{0}.{1}",
                        connection.getMetaData().getDatabaseMajorVersion(),
                        connection.getMetaData().getDatabaseMinorVersion()
                    )
                )
            )
        );
    }

    /**
     * Instantiates a new Dbd server mapping with credentials.
     * @param mapping The DbdServerMapping to merge into.
     * @param url The Text to be encapsulated.
     * @param user The Text to be encapsulated.
     * @param password The Text to be encapsulated.
     * @checkstyle ParameterNumberCheck (20 lines)
     */
    public DbdServerMappingWithCredentials(
        final DbdServerMapping mapping,
        final Text url,
        final Text user,
        final Text password
    ) {
        this(
            mapping,
            new ConnectionOfTextArgs(url, user, password),
            url,
            user,
            password
        );
    }

    /**
     * Instantiates a new Dbd server mapping with credentials.
     * @param url The Text to be encapsulated.
     * @param user The Text to be encapsulated.
     * @param password The Text to be encapsulated.
     */
    public DbdServerMappingWithCredentials(
        final Text url,
        final Text user,
        final Text password
    ) {
        this(
            new DbdServerMapping(
                new MappingWithKeys(
                    DbdServerFields.values()
                )
            ),
            new ConnectionOfTextArgs(url, user, password),
            url, user, password
        );
    }

    /**
     * Instantiates a new Dbd server mapping with credentials.
     * @param url The Text of database url to be encapsulated.
     * @param credentials The Credentials to be encapsulated.
     */
    public DbdServerMappingWithCredentials(
        final Text url,
        final Credentials credentials
    ) {
        this(
            url,
            new TextOfScalar(credentials::username),
            new TextOfScalar(credentials::password)
        );
    }

}
