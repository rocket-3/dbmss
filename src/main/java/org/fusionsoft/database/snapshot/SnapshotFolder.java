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

import java.io.File;
import java.nio.file.Path;
import org.cactoos.Text;

/**
 * The type representing folder of some Snapshot by given name.
 * @since 0.1
 * @todo #15:15min Implement correct `path` method behaviour.
 * @checkstyle NonStaticMethodCheck (100 lines)
 */
@SuppressWarnings("PMD")
public class SnapshotFolder {

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
     * @param path The Path to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    public SnapshotFolder(final Path path, final Text name) {
        this.path = path;
        this.name = name;
    }

    /**
     * Path path.
     * @return The path.
     */
    public final Path path() {
        return new File("").toPath();
    }
}
