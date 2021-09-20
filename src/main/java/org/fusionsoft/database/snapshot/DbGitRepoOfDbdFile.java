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
import org.fusionsoft.database.DbdFile;

/**
 * The type representing dbgit repo in which {@link DbdFile} is placed.
 * @since 0.1
 * @todo #40:15min Specify relation of `Dbd file` and `DbGit repo`.
 * @checkstyle NonStaticMethodCheck (100 lines)
 */
@SuppressWarnings("PMD")
public class DbGitRepoOfDbdFile {

    /**
     * The DbdFile encapsulated.
     */
    private final DbdFile file;

    /**
     * Instantiates a new Db git repo of dbd file.
     * @param file The DbdFile to be encapsulated.
     */
    public DbGitRepoOfDbdFile(final DbdFile file) {
        this.file = file;
    }

    /**
     * Path path.
     * @return The path.
     */
    public final Path path() {
        return new File("").toPath();
    }
}
