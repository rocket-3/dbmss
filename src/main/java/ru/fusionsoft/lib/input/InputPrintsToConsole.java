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
import org.cactoos.Input;
import org.cactoos.Text;

/**
 * The type of Input that prints text to {@link org.cactoos.io.Stdout} at usage.
 * @since 0.1
 */
public class InputPrintsToConsole implements Input {

    /**
     * The printing Runnable encapsulated.
     */
    private final Runnable printing;

    /**
     * The Input encapsulated.
     */
    private final Input origin;

    /**
     * Instantiates a new Input prints to console.
     * @param promt The Text to be printed at usage.
     * @param origin The Input to be encapsulated.
     */
    public InputPrintsToConsole(final Text promt, final Input origin) {
        this.printing = new ConsolePrinting(promt);
        this.origin = origin;
    }

    @Override
    public final InputStream stream() throws Exception {
        this.printing.run();
        return this.origin.stream();
    }

}
