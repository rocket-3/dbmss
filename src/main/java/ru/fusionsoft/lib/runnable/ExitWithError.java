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

import org.cactoos.Text;
import org.cactoos.text.TextOf;

/**
 * The {@link Runnable} of exiting console application with message to stderr and code 1.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ExitWithError implements Runnable {

    /**
     * The Text encapsulated.
     */
    private final Text message;

    /**
     * Instantiates a new Exit with error.
     * @param message The {@link Text} to be encapsulated.
     */
    public ExitWithError(final Text message) {
        this.message = message;
    }

    /**
     * Instantiates a new Exit with error.
     * @param message The {@link String} to be encapsulated.
     */
    public ExitWithError(final String message) {
        this(new TextOf(message));
    }

    /**
     * Instantiates a new Exit with error.
     * @param message The array of {@link String} to be encapsulated.
     */
    public ExitWithError(final String... message) {
        this(new TextOf(String.join("", message)));
    }

    @Override
    public final void run() {
        System.out.println(this.message);
        System.err.println(this.message);
        System.exit(1);
    }

}
