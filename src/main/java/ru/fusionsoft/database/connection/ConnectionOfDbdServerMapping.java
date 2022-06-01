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
package ru.fusionsoft.database.connection;

import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.fields.DbdServerFields;
import ru.fusionsoft.lib.connection.ConnectionOfTextArgs;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * The type of {@link java.sql.Connection} of {@link DbdServerMapping}.
 * @since 0.1
 */
public class ConnectionOfDbdServerMapping extends ConnectionOfTextArgs {

    /**
     * Instantiates a new Connection of scalar.
     * @param mapping The mapping to use.
     */
    public ConnectionOfDbdServerMapping(final DbdServerMapping mapping) {
        super(
            new TextOfYamlMappingKeyValue(mapping, DbdServerFields.URL),
            new TextOfYamlMappingKeyValue(mapping, DbdServerFields.USER),
            new TextOfYamlMappingKeyValue(mapping, DbdServerFields.PWD)
        );
    }

}
