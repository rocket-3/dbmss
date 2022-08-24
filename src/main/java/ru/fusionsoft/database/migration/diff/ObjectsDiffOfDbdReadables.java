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
package ru.fusionsoft.database.migration.diff;

import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfDbdReadable;

/**
 * The {@link ObjectsDiff} constructed of {@link DbdReadable}s.
 * @since 0.1
 */
public class ObjectsDiffOfDbdReadables extends ObjectsDiff {

    /**
     * Instantiates a new Objects diff of {@link DbdReadable}.
     * @param current The {@link DbdReadable} to be encapsulated.
     * @param next The {@link DbdReadable} to be encapsulated.
     */
    public ObjectsDiffOfDbdReadables(final DbdReadable current, final DbdReadable next) {
        super(
            new ObjectsOfDbdReadable(current),
            new ObjectsOfDbdReadable(next)
        );
    }

    /**
     * Instantiates a new Objects diff of dbd readables.
     * @param files The {@link TemporalDiff} of {@link DbdReadable}s to be encapsulated.
     */
    public ObjectsDiffOfDbdReadables(final TemporalDiff<DbdReadable> files) {
        this(
            files.current(),
            files.next()
        );
    }

}
