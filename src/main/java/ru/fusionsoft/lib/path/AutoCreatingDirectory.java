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
import org.cactoos.scalar.Sticky;

/**
 * The {@link Directory} that is created on usage, but only first time.
 * @since 0.1
 */
public class AutoCreatingDirectory extends DirectoryOfScalar {

    /**
     * Instantiates a new auto creating {@link Directory}.
     * @param scalar The {@link Scalar} of {@link Path} to be encapsulated.
     */
    public AutoCreatingDirectory(final Scalar<Path> scalar) {
        super(
            new Sticky<>(
                () -> {
                    final Path path = scalar.value();
                    path.toFile().mkdirs();
                    return path;
                }
            )
        );
    }

}
