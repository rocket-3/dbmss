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

import java.nio.file.Path;
import java.util.Random;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.Concatenated;

public class TempFilePath implements Scalar<Path> {

    private final Unchecked<Path> scalar;

    private TempFilePath(final Scalar<Path> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    public TempFilePath(final Scalar<Path> inside, final CharSequence prefix) {
        this(
            () -> {
                return inside.value().resolve(
                    new Concatenated(
                        prefix,
                        String.format(
                            "#%06x",
                            new Random().nextInt(256 * 256 * 256)
                        )
                    ).asString()
                );
            }
        );
    }
    public TempFilePath(final Path inside, final CharSequence prefix) {
        this(
            () -> inside,
            prefix
        );
    }

    @Override
    public Path value() {
        return this.scalar.value();
    }

}
