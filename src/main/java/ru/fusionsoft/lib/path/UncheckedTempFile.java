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
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.io.TempFile;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.runnable.WithRethrowAsUnchecked;

/**
 * The {@link TempFile}, but throws no checked exceptions.
 * @since 0.1
 */
public class UncheckedTempFile implements Scalar<Path>, AutoCloseable {

    /**
     * The TempFile encapsulated.
     */
    private final TempFile file;

    /**
     * Instantiates a new Unchecked temp file.
     * @param dir The {@link Scalar} of {@link Path} to be encapsulated.
     * @param prefix The {@link Text} to be encapsulated.
     * @param suffix The {@link Text} to be encapsulated.
     */
    public UncheckedTempFile(final Scalar<Path> dir, final Text prefix, final Text suffix) {
        this.file = new TempFile(dir, prefix, suffix);
    }

    /**
     * Instantiates a new Unchecked temp file.
     * @param dir The {@link Scalar} of {@link Path} to be encapsulated.
     * @param prefix The {@link String} to be encapsulated.
     * @param suffix The {@link String} to be encapsulated.
     */
    public UncheckedTempFile(final Scalar<Path> dir, final String prefix, final String suffix) {
        this(dir, new TextOf(prefix), new TextOf(suffix));
    }

    /**
     * Instantiates a new Unchecked temp file.
     * @param dir The {@link Scalar} of {@link Path} to be encapsulated.
     */
    public UncheckedTempFile(final Scalar<Path> dir) {
        this(dir, "", "");
    }

    @Override
    public final void close() {
        new WithRethrowAsUnchecked().exec(
            this.file::close
        );
    }

    @Override
    public final Path value() {
        return new Unchecked<>(this.file).value();
    }

}
