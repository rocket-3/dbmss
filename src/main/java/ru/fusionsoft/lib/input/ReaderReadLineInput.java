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
package ru.fusionsoft.lib.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.io.InputStreamOf;
import org.cactoos.scalar.ScalarOf;

/**
 * The type of Input that decorates reader and reads first line from decorated.
 * @since 0.1
 */
public class ReaderReadLineInput implements Input {

    /**
     * The Scalar of InputStream encapsulated.
     */
    private final Scalar<InputStream> scalar;

    /**
     * Instantiates a new Reader read line input.
     * @param reader The BufferedReader to be encapsulated.
     */
    private ReaderReadLineInput(final BufferedReader reader) {
        this.scalar = new ScalarOf<InputStream>(
            br -> new InputStreamOf(
                br.readLine()
            ),
            reader
        );
    }

    /**
     * Instantiates a new instance from {@link Reader}.
     * @param reader The Reader to be encapsulated.
     */
    public ReaderReadLineInput(final Reader reader) {
        this(
            new BufferedReader(reader)
        );
    }

    @Override
    public final InputStream stream() throws Exception {
        return this.scalar.value();
    }

}
