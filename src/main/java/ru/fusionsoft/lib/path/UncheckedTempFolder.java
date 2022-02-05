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
package ru.fusionsoft.lib.path;

import java.nio.file.Path;
import org.cactoos.Text;
import org.cactoos.io.TempFolder;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.lib.runnable.WithRethrowAsUnchecked;

/**
 * The {@link TempFolder}, but throws no checked exceptions.
 * @since 0.1
 */
public class UncheckedTempFolder implements Directory, AutoCloseable {

    /**
     * The TempFolder encapsulated.
     */
    private final TempFolder folder;

    /**
     * Instantiates a new Unchecked temp folder.
     * @param folder The {@link TempFolder} to be encapsulated.
     */
    public UncheckedTempFolder(final TempFolder folder) {
        this.folder = folder;
    }

    /**
     * Instantiates a new Unchecked temp folder.
     */
    public UncheckedTempFolder() {
        this(new TempFolder());
    }

    /**
     * Instantiates a new Unchecked temp folder.
     * @param path The {@link Text} to be encapsulated.
     */
    public UncheckedTempFolder(final Text path) {
        this(new TempFolder(path));
    }

    @Override
    public final Path value() {
        return new Unchecked<>(this.folder).value();
    }

    @Override
    public final void close() {
        new WithRethrowAsUnchecked().exec(
            this.folder::close
        );
    }

}
