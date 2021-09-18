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

import org.fusionsoft.database.snapshot.DbObjects;
import org.fusionsoft.database.snapshot.SnapshotFolder;
import org.fusionsoft.database.snapshot.Writable;

/**
 * The type of {@link Writable} that represents db tables data
 *  of given {@link DbObjects}.
 * @since 0.1
 * @todo #15:15min Implement later in other puzzle
 * @checkstyle (100 lines)
 */
@SuppressWarnings("PMD")
public class TableDataFilesOfObjects implements Writable {

    /**
     * Instantiates a new Table data files of objects.
     * @param objects The DbObjects to be encapsulated.
     */
    public TableDataFilesOfObjects(final DbObjects objects) {
    }

    @Override
    public void writeTo(final SnapshotFolder folder) {
    }

}
