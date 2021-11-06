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
package org.fusionsoft.database.mapping.dbd.built;

import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.dbd.DbdServerMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdServerFields;
import org.fusionsoft.lib.ci.credentials.Credentials;
import org.fusionsoft.lib.yaml.MappingWithEntries;
import org.fusionsoft.lib.yaml.MappingWithKeys;

/**
 * The type of {@link DbdServerMapping} with {@link Credentials} values merged into.
 * @since 0.1
 */
public class DbdServerMappingWithCredentials extends DbdServerMapping {

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
                )
            )
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
