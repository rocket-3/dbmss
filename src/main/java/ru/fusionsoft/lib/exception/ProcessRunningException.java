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
package ru.fusionsoft.lib.exception;

import java.text.MessageFormat;
import java.util.List;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.Newline;
import org.cactoos.text.Split;
import org.cactoos.text.UncheckedText;

/**
 * The type of {@link RuntimeException}, thrown when process exits with non-zero code.
 * @since 0.1
 */
public class ProcessRunningException extends RuntimeException {

    /**
     * Instantiates a new Unchecked of checked.
     * @param code The {@link int} to be encapsulated.
     * @param lines The {@link List} of {@link Text} to be encapsulated.
     */
    public ProcessRunningException(final int code, final List<Text> lines) {
        super(
            new UncheckedText(
                () -> MessageFormat.format(
                    "Process exited with error, exit code {0}\nErrors: {1}",
                    code,
                    new Ternary<>(
                        lines::isEmpty,
                        () -> "The error stream was empty.",
                        () -> String.join(
                            "",
                            new Mapped<String>(
                                line -> MessageFormat.format("\n> {0}", line),
                                lines
                            )
                        )
                    ).value()
                )
            ).asString()
        );
    }

    /**
     * Instantiates a new Unchecked of checked.
     * @param code The {@link int} to be encapsulated.
     * @param message The {@link Text} to be encapsulated.
     */
    public ProcessRunningException(final int code, final Text message) {
        this(
            code,
            new ListOf<Text>(
                new Split(
                    message,
                    new Newline()
                )
            )
        );
    }

}
