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
package org.fusionsoft.database.dbobject;

import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Server;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The collection of DbObjects extracting from server.
 * @see Server
 * @since 0.1
 */
public class DbObjectsFromServer extends CollectionEnvelope<DbObject> {

    /**
     * Instantiates a new Db objects from server.
     * @param server The server to extract objects from.
     */
    public DbObjectsFromServer(final Server server) {
        super(
            new ListOf<>(
                new IterableOf<>(
                    () -> {
                        server.notifyAll();
                        throw new NotImplemented();
                    }
                )
            )
        );
    }

}
