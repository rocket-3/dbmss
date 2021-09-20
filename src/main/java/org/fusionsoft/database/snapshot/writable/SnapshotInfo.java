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
package org.fusionsoft.database.snapshot.writable;

import org.fusionsoft.database.snapshot.AstronomicalTime;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.database.snapshot.SnapshotFolder;
import org.fusionsoft.database.snapshot.SnapshotName;
import org.fusionsoft.database.snapshot.Writable;

/**
 * The type {@link Writable} that represents db objects snapshot info data.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 * @checkstyle ParameterNameCheck (100 lines)
 * @todo #40:60min Implement `SnapshotInfo` `Writable`.
 */
@SuppressWarnings("PMD")
public class SnapshotInfo implements Writable {

    /**
     * Instantiates a new Snapshot info.
     * @param name The SnapshotName to be encapsulated.
     * @param time The AstronomicalTime to be encapsulated.
     * @param database The DatabaseInfo to be encapsulated.
     * @param withOperationalData The Boolean to be encapsulated.
     */
    public SnapshotInfo(
        final SnapshotName name,
        final AstronomicalTime time,
        final DatabaseInfo database,
        final Boolean withOperationalData
    ) {
    }

    @Override
    public void writeTo(final SnapshotFolder folder) {
    }

}
