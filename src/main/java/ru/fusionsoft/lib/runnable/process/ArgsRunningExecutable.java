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
package ru.fusionsoft.lib.runnable.process;

import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOfScalar;

/**
 * The Iterable of String command line args of running executable from specific path.
 * @since 0.1
 */
public class ArgsRunningExecutable extends ArgsAppending {

    /**
     * Instantiates a new Args running executable.
     * @param executable The {@link Text} to be encapsulated.
     * @param args The {@link Iterable} of {@link String} to be encapsulated.
     */
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    public ArgsRunningExecutable(final Text executable, final Iterable<String> args) {
        super(
            args,
            new IterableOf<>(
                new Ternary<>(
                    () -> System.getenv("ComSpec") == null,
                    () -> new IteratorOf<>(executable.asString()),
                    () -> new IteratorOf<>(
                        System.getenv("ComSpec"),
                        "/C",
                        executable.asString()
                    )
                )
            )
        );
    }

    /**
     * Instantiates a new Args running executable.
     * @param executable The {@link String} to be encapsulated.
     * @param args The array of String to be encapsulated.
     */
    public ArgsRunningExecutable(final String executable, final String... args) {
        this(
            new TextOfScalar(() -> executable),
            new IterableOf<>(args)
        );
    }

    /**
     * Instantiates a new Args running executable.
     * @param executable The {@link Scalar} of Path to be encapsulated.
     * @param args The {@link Iterable} of String to be encapsulated.
     */
    public ArgsRunningExecutable(final Scalar<Path> executable, final Iterable<String> args) {
        this(
            new TextOfScalar(() -> executable.value().toString()),
            args
        );
    }

}
