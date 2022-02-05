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
import org.cactoos.scalar.Unchecked;

/**
 * The {@link Directory} that creates of {@link Scalar}/{@link java.util.function.Supplier}.
 * @since 0.1
 */
public class DirectoryOfScalar implements Directory {

    /**
     * The of {@link Path} encapsulated.
     */
    private final Scalar<Path> scalar;

    /**
     * Instantiates a new Folder of scalar.
     * @param scalar The of {@link Path} to be encapsulated.
     */
    public DirectoryOfScalar(final Scalar<Path> scalar) {
        this.scalar = scalar;
    }

    @Override
    public final Path value() {
        return new Unchecked<>(this.scalar).value();
    }

}
