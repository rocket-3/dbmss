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
package org.fusionsoft.lib.input;

import java.io.OutputStream;
import java.io.PrintStream;
import org.cactoos.Text;

/**
 * The {@link Runnable} that prints {@link Text} to {@link OutputStream}.
 * @since 0.1
 */
public class OutputStreamPrinting implements Runnable {

    /**
     * The PrintStream encapsulated.
     */
    private final PrintStream stream;

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * Instantiates a new OutputStreamPrinting.
     * @param stream The PrintStream to be used for printing.
     * @param text The Text to be printed.
     */
    public OutputStreamPrinting(
        final PrintStream stream,
        final Text text
    ) {
        this.stream = stream;
        this.text = text;
    }

    /**
     * Instantiates a new OutputStreamPrinting.
     * @param stream The OutputStream to be used for printing.
     * @param text The Text to be printed.
     */
    public OutputStreamPrinting(
        final OutputStream stream,
        final Text text
    ) {
        this(new PrintStream(stream), text);
    }

    @Override
    public final void run() {
        this.stream.println(this.text);
    }

}
