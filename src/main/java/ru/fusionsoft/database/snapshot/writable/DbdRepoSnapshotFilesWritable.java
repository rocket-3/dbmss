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
package ru.fusionsoft.database.snapshot.writable;

import ru.fusionsoft.database.snapshot.data.SeparateDataFilesOfTables;
import ru.fusionsoft.lib.path.Writable;
import ru.fusionsoft.lib.path.writable.JoinedWritable;

/**
 * The {@link Writable} of snapshot files.
 * @since 0.1
 */
public class DbdRepoSnapshotFilesWritable extends JoinedWritable {

    /**
     * Instantiates a new Snapshot files.
     * @param info The {@link SnapshotInfoDocument} to be encapsulated.
     * @param dbd The {@link DbdYamlWritable} to be encapsulated.
     * @param data The {@link SeparateDataFilesOfTables} to be encapsulated.
     */
    public DbdRepoSnapshotFilesWritable(
        final SnapshotInfoDocument info,
        final DbdYamlWritable dbd,
        final SeparateDataFilesOfTables data
    ) {
        super(
            info,
            dbd,
            data
        );
    }

}
