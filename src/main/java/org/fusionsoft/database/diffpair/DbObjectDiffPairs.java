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
package org.fusionsoft.database.diffpair;

import java.util.Collection;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterator.IteratorOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;

/**
 * The type of Iterable of DiffPair of DbObjects that can be
 * constructed from two collections, inserting {@link DbObject.ABSENT}
 * when there's no matching pair for some object from other collection.
 * @since 0.1
 */
public class DbObjectDiffPairs extends IterableEnvelope<DiffPair<DbObject>> {

    /**
     * Instantiates a new Db object diff pairs.
     * @param persistent The Collection DbObject to be encapsulated.
     * @param target The Collection DbObject to be encapsulated.
     */
    public DbObjectDiffPairs(
        final Collection<DbObject> persistent,
        final Collection<DbObject> target
    ) {
        super(
            new IterableOf<>(
                () -> {
                    persistent.notifyAll();
                    target.notifyAll();
                    return new IteratorOf<DiffPair<DbObject>>();
                }
            )
        );
    }

}
