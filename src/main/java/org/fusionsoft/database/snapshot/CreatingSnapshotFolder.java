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
package org.fusionsoft.database.snapshot;

import java.nio.file.Path;
import org.cactoos.Text;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.Folder;

/**
 * The type representing folder of some Snapshot by given name.
 * @since 0.1
 * @checkstyle NonStaticMethodCheck (100 lines)
 */
@SuppressWarnings("PMD")
public class CreatingSnapshotFolder implements Folder {

    /**
     * The Path encapsulated.
     */
    private final Path path;

    /**
     * The Text encapsulated.
     */
    private final Text name;

    /**
     * Instantiates a new Snapshot folder.
     * @param dbgit The Path of .dbgit to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    private CreatingSnapshotFolder(final Path dbgit, final Text name) {
        this.path = dbgit;
        this.name = name;
    }

    /**
     * Instantiates a new Snapshot folder.
     * @param dbgit The Path of .dbgit to be encapsulated.
     * @param time The AstronomicalTime of creation to be encapsulated.
     */
    public CreatingSnapshotFolder(final Path dbgit, final AstronomicalTime time) {
        this(dbgit, new SnapshotCatalogName(time));
    }

    @Override
    public final Path path() {
        final Path resolve = this.path.resolve(
            new UncheckedText(this.name).asString()
        );
        resolve.toFile().mkdir();
        return resolve;
    }

}
