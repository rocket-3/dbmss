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
package org.fusionsoft.database.writable;

import org.cactoos.iterable.IterableOf;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.Writable;
import org.fusionsoft.database.mapping.dbd.DbdInfoMapping;
import org.fusionsoft.database.mapping.dbd.DbdRootMappingBuilt;
import org.fusionsoft.database.mapping.dbd.DbdServerEntry;
import org.fusionsoft.database.mapping.dbd.DbdServersMapping;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The type of {@link Writable} representing Dbd yaml file,
 *  describing {@link Objects}.
 * @since 0.1
 * @todo #40:60min Implement `DbdYamlOfObjects` `Writable`
 */
@SuppressWarnings("PMD")
public class DbdSnapshotDocument extends WritableYamlDocument {

    /**
     * Instantiates a new Dbd yaml of objects.
     * @param server The DbdServerEntry to be encapsulated.
     * @param info The DbdInfoMapping to be encapsulated.
     * @param objects The Objects to be encapsulated.
     */
    public DbdSnapshotDocument(
        final DbdServerEntry server,
        final DbdInfoMapping info,
        final Objects objects
    ) {
        super(
            new DbdRootMappingBuilt(
                new DbdServersMapping(
                    new IterableOf<>(
                        server
                    )
                ),
                info,
                objects
            ),
            new TextOf("DBD.yaml")
        );
    }

}