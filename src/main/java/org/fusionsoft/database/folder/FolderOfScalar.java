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
package org.fusionsoft.database.folder;

import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.Folder;

/**
 * The {@link Folder} that creates of {@link Scalar}/{@link java.util.function.Supplier}.
 * @since 0.1
 */
public class FolderOfScalar implements Folder {

    /**
     * The of {@link Path} encapsulated.
     */
    private final Scalar<Path> scalar;

    /**
     * Instantiates a new Folder of scalar.
     * @param scalar The of {@link Path} to be encapsulated.
     */
    public FolderOfScalar(final Scalar<Path> scalar) {
        this.scalar = scalar;
    }

    @Override
    public final Path path() {
        return new Unchecked<>(this.scalar).value();
    }

}
