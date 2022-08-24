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
package ru.fusionsoft.database.migration.typecasts.mapping;

import java.sql.Connection;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.typecasts.TypecastsOfObjectsDiffAndConnection;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfConnection;

/**
 * {@link ru.fusionsoft.database.mapping.config.TypecastsMapping}, constructed
 *  of {@link  TypecastsOfObjectsDiffAndConnection}.
 * @since 0.1
 */
public class TypecastsMappingOfConnectionAndObjectsDiff extends TypecastsMappingOfTypecasts {

    /**
     * Instantiates a new Typecasts mapping of connection and objects diff.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     */
    public TypecastsMappingOfConnectionAndObjectsDiff(
        final DbdServerMapping server,
        final ObjectsDiff diff
    ) {
        this(
            new ConnectionOfDbdServerMapping(server),
            diff
        );
    }

    /**
     * Instantiates a new Typecasts mapping of connection and objects diff.
     * @param connection The {@link Connection} to be encapsulated.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     */
    public TypecastsMappingOfConnectionAndObjectsDiff(
        final Connection connection,
        final ObjectsDiff diff
    ) {
        super(
            new DbmsOfConnection(connection),
            new TypecastsOfObjectsDiffAndConnection(diff, connection)
        );
    }

}
