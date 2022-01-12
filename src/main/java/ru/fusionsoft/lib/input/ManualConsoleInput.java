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

import java.io.InputStream;
import java.io.InputStreamReader;
import org.cactoos.Input;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.io.Stdin;
import org.cactoos.scalar.ScalarOf;

/**
 * The type of Input that uses console and returns at 'Enter' key being pressed.
 * @since 0.1
 */
public class ManualConsoleInput implements Input {

    /**
     * The Scalar of Input encapsulated.
     */
    private final Scalar<Input> scalar;

    /**
     * Instantiates a new Manual console input.
     * @param charset The Text to be encapsulated.
     */
    public ManualConsoleInput(final Text charset) {
        this.scalar = new ScalarOf<Input>(
            chrset -> new ReaderReadLineInput(
                new InputStreamReader(
                    new Stdin().stream(),
                    chrset.asString()
                )
            ),
            charset
        );
    }

    @Override
    public final InputStream stream() throws Exception {
        return this.scalar.value().stream();
    }

}
