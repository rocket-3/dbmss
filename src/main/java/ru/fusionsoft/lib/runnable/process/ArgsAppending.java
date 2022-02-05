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

import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;

/**
 * The concatenated {@link Iterable} of {@link String}s.
 * @since 0.1
 */
public class ArgsAppending extends ArgsExplicit {

    /**
     * Instantiates a new Args appending.
     * @param append The {@link Iterable} of {@link String} to be encapsulated.
     * @param args The {@link Iterable} of {@link String} to be encapsulated.
     */
    public ArgsAppending(final Iterable<String> append, final Iterable<String> args) {
        super(
            new Joined<>(
                new IterableOf<>(
                    args,
                    append
                )
            )
        );
    }

}
