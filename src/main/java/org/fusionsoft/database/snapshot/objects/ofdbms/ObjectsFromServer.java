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
package org.fusionsoft.database.snapshot.objects.ofdbms;

import com.amihaiemil.eoyaml.YamlMapping;
import java.sql.Connection;
import org.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import org.fusionsoft.database.mapping.dbd.DbdServerMapping;
import org.fusionsoft.database.snapshot.dbms.DbmsOfConnection;

/**
 * The DbObjects from {@link DbdServerMapping}.
 * @since 0.1
 */
public class ObjectsFromServer extends ObjectsOfConnection<YamlMapping> {

    /**
     * Ctor.
     * @param server The server used to extract data.
     */
    public ObjectsFromServer(final DbdServerMapping server) {
        this(new ConnectionOfDbdServerMapping(server));
    }

    /**
     * Ctor.
     * @param connection The connection used to extract data.
     */
    public ObjectsFromServer(final Connection connection) {
        super(
            connection,
            new DbmsOfConnection(connection).objects()
        );
    }

}