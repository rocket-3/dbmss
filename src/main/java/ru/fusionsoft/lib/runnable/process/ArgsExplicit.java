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

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;

/**
 * The Iterable of String, representing command line arguments.
 * @since 0.1
 */
public class ArgsExplicit extends Args {

    /**
     * Instantiates a new empty Args.
     */
    public ArgsExplicit() {
        super(new IterableOf<>());
    }

    /**
     * Ctor.
     * @param args The {@link Iterable} of {@link String} to be encapsulated.
     */
    public ArgsExplicit(final Iterable<String> args) {
        super(args);
    }

    /**
     * Ctor.
     * @param args Encapsulated args array.
     */
    public ArgsExplicit(final String... args) {
        this(new IterableOf<>(args));
    }

    /**
     * Ctor.
     * @param args Encapsulated args array.
     */
    public ArgsExplicit(final Text... args) {
        this(new Mapped<>(Text::asString, new IterableOf<>(args)));
    }

}
