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
package ru.fusionsoft.database.snapshot;

import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.database.Folder;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;

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
    private final Scalar<Path> root;

    /**
     * The Text encapsulated.
     */
    private final Text name;

    /**
     * Instantiates a new Snapshot folder.
     * @param dbgit The Path of .dbgit to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    private CreatingSnapshotFolder(final Scalar<Path> dbgit, final Text name) {
        this.root = dbgit;
        this.name = name;
    }

    /**
     * Instantiates a new Snapshot folder.
     * @param time The AstronomicalTime of creation to be encapsulated.
     */
    public CreatingSnapshotFolder(final AstronomicalTime time) {
        this(
            new CurrentWorkingDirectory(),
            new SnapshotCatalogName(time)
        );
    }

    @Override
    public final Path path() {
        final Path resolve = new Unchecked<>(this.root).value().resolve(
            new UncheckedText(this.name).asString()
        );
        resolve.toFile().mkdirs();
        return resolve;
    }

}
