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
package ru.fusionsoft.database.snapshot.data;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.dbd.DbdDataMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The Iterable of {@link LinkDataObjectOfTable},
 *  can be constructed from {@link Iterable} of {@link DbObject}s of {@link DbdTableMapping}.
 * @since 0.1
 */
public class LinkDataObjectsOfTables extends IterableEnvelope<DbObject<DbdDataMapping>> {

    /**
     * Instantiates a new Link data objects of tables.
     * @param tables The {@link Iterable} of {@link DbObject}s of {@link DbdTableMapping}
     *  to be encapsulated.
     */
    public LinkDataObjectsOfTables(final Iterable<DbObject<DbdTableMapping>> tables) {
        super(
            new Mapped<>(
                LinkDataObjectOfTable::new,
                tables
            )
        );
    }

}
