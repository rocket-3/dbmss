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
package ru.fusionsoft.lib.runnable;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;
import org.cactoos.Output;
import org.cactoos.Scalar;
import org.cactoos.func.Chained;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.io.UncheckedOutput;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.path.UncheckedTempFile;
import ru.fusionsoft.lib.path.UncheckedTempFileAndFolder;
import ru.fusionsoft.lib.runnable.process.ProcessExitHandle;
import ru.fusionsoft.lib.runnable.process.ProcessExitThrowErrors;
import ru.fusionsoft.lib.text.TextOfCallCommandAtPath;

/**
 * The {@link Runnable} that runs process, which prints to {@link Output}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 * @checkstyle ParameterNumberCheck (200 lines)
 */
public class ProcessRunnable implements Runnable {

    /**
     * The Scalar of Path encapsulated.
     */
    private final Scalar<Path> origin;

    /**
     * The Iterable of String encapsulated.
     */
    private final Iterable<String> command;

    /**
     * The Iterable of String encapsulated.
     */
    private final Iterable<String> input;

    /**
     * The Output encapsulated.
     */
    private final Output output;

    /**
     * The ProcessExitHandle encapsulated.
     */
    private final ProcessExitHandle exit;

    /**
     * Instantiates a new Process runnable.
     * @param origin The working directory to be encapsulated.
     * @param command The command to call.
     * @param input The process input stream lines to enter.
     * @param output The {@link Output} of process to be encapsulated.
     * @param exit The {@link ProcessExitHandle} to be encapsulated.
     */
    public ProcessRunnable(
        final Scalar<Path> origin,
        final Iterable<String> command,
        final Iterable<String> input,
        final Output output,
        final ProcessExitHandle exit
    ) {
        this.origin = origin;
        this.command = command;
        this.input = input;
        this.output = output;
        this.exit = exit;
    }

    /**
     * Instantiates a new Process runnable.
     * @param origin The working directory to be encapsulated.
     * @param command The command to call.
     * @param output The {@link Output} of process to be encapsulated.
     * @param exit The {@link ProcessExitHandle} to be encapsulated.
     */
    public ProcessRunnable(
        final Scalar<Path> origin,
        final Iterable<String> command,
        final Output output,
        final ProcessExitHandle exit
    ) {
        this(origin, command, new IterableOf<String>(), output, exit);
    }

    /**
     * Instantiates a new Process runnable.
     * @param origin The working directory to be encapsulated.
     * @param command The command to call.
     * @param output The {@link Output} of process to be encapsulated.
     */
    public ProcessRunnable(
        final Scalar<Path> origin,
        final Iterable<String> command,
        final Output output
    ) {
        this(
            origin,
            command,
            new IterableOf<String>(),
            output,
            new ProcessExitThrowErrors()
        );
    }

    @Override
    public final void run() {
        try (UncheckedTempFile err = new UncheckedTempFileAndFolder()) {
            final PrintStream print = new PrintStream(new UncheckedOutput(this.output).stream());
            print.println(
                new TextOfCallCommandAtPath(
                    this.origin,
                    this.command
                ).asString()
            );
            final Integer code = new UncheckedFunc<>(
                new Chained<ProcessBuilder, Process, Integer>(
                    new Chained<>(
                        builder -> builder,
                        new IterableOf<>(
                            builder -> builder.directory(this.origin.value().toFile()),
                            builder -> builder.command(new ListOf<>(this.command)),
                            builder -> builder.redirectError(err.value().toFile())
                        ),
                        ProcessBuilder::start
                    ),
                    process -> {
                        final Thread thread = new Thread(
                            () -> {
                                final Scanner out = new Scanner(process.getInputStream());
                                while (out.hasNextLine()) {
                                    print.println(out.nextLine());
                                }
                            }
                        );
                        thread.start();
                        try (PrintStream istream = new PrintStream(process.getOutputStream())) {
                            for (final String line : this.input) {
                                istream.println(line);
                            }
                        }
                        process.getOutputStream().close();
                        final int result = process.waitFor();
                        thread.join();
                        return result;
                    }
                )
            ).apply(new ProcessBuilder());
            this.exit.handle(
                code,
                new TextOf(err.value().toFile()),
                this.output
            );
        }
    }

}
