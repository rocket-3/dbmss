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

import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.lib.exception.ProcessRunningException;
import ru.fusionsoft.lib.runnable.ProcessRunnable;
import ru.fusionsoft.lib.runnable.process.ProcessExitThrowErrors;

/**
 * The text of running command output, supports setting working directory and stdin lines,
 *  throws {@link ProcessRunningException} when exit code is not 0.
 * @since 0.1
 */
public class TextOfRunningCommandOutputWithException implements Text {

    /**
     * The Scalar of Path encapsulated.
     */
    private final Scalar<Path> origin;

    /**
     * The command args encapsulated.
     */
    private final Iterable<String> command;

    /**
     * The command input encapsulated.
     */
    private final Iterable<String> input;

    /**
     * Instantiates a new Text of running command output with exception.
     * @param origin The working directory to be encapsulated.
     * @param command The command args to be encapsulated.
     * @param input The command input lines to be encapsulated.
     */
    public TextOfRunningCommandOutputWithException(
        final Scalar<Path> origin,
        final Iterable<String> command,
        final Iterable<String> input
    ) {
        this.origin = origin;
        this.command = command;
        this.input = input;
    }

    /**
     * Instantiates a new Text of running command output with exception.
     * @param origin The working directory to be encapsulated.
     * @param command The command args to be encapsulated.
     */
    public TextOfRunningCommandOutputWithException(
        final Scalar<Path> origin,
        final Iterable<String> command
    ) {
        this(origin, command, new IterableOf<>());
    }

    @Override
    public final String asString() throws ProcessRunningException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ProcessRunnable(
            this.origin,
            this.command,
            this.input,
            () -> baos,
            new ProcessExitThrowErrors()
        ).run();
        return baos.toString();
    }

}
