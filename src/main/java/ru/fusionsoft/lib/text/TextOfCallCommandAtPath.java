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
package ru.fusionsoft.lib.text;

import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.text.TextOf;

/**
 * The {@link org.cactoos.Text} of console command call from specific path (working directory).
 * @since 0.1
 */
public class TextOfCallCommandAtPath extends TextOfMessageFormat {

    /**
     * Instantiates a new Text of command call.
     * @param origin The Scalar of Path to be encapsulated.
     * @param command The Iterable of String to be encapsulated.
     */
    public TextOfCallCommandAtPath(final Scalar<Path> origin, final Iterable<String> command) {
        super(
            new TextOf("{0}>{1}"),
            () -> origin.value().toString(),
            () -> String.join(" ", command)
        );
    }

}
