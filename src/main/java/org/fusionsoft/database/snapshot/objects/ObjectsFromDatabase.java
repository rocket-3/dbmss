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
package org.fusionsoft.database.snapshot.objects;

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterator.IteratorOf;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The DbObjects from {@link DatabaseInfo}.
 * @since 0.1
 * @todo #46:30min Start implementing.
 */
@SuppressWarnings("PMD")
public class ObjectsFromDatabase extends IterableEnvelope<DbObject> implements Objects {

    /**
     * Ctor.
     * @param database The database used to extract data.
     */
    public ObjectsFromDatabase(final DatabaseInfo database) {
        super(
            new IterableOf<DbObject>(
                new IteratorOf<>()
            )
        );
    }

}
