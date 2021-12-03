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
package org.fusionsoft.lib.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

public class AutoDeletingPath implements Scalar<Path>, AutoCloseable {

    private final Unchecked<Path> scalar;

    public AutoDeletingPath(final Scalar<Path> origin) {
        this.scalar = new Unchecked<>(origin);
    }

    public AutoDeletingPath(final Path origin) {
        this(() -> origin);
    }

    @Override
    public final void close() throws IOException {
        Files.deleteIfExists(this.scalar.value());
    }

    @Override
    public Path value() {
        return this.scalar.value();
    }

}
