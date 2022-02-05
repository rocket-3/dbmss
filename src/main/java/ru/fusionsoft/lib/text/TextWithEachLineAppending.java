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

import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sticky;
import org.cactoos.text.Joined;
import org.cactoos.text.Split;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The Text, decorating another, but with some text appending and prepending to each line.
 * @since 0.1
 */
public class TextWithEachLineAppending implements Text {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The Text encapsulated.
     */
    private final Text appending;

    /**
     * The Text encapsulated.
     */
    private final Text prepending;

    /**
     * Instantiates a new Text with each line appending.
     * @param prepending The {@link Text} to be encapsulated.
     * @param text The {@link Text} to be encapsulated.
     * @param appending The {@link Text} to be encapsulated.
     */
    public TextWithEachLineAppending(final Text prepending, final Text text, final Text appending) {
        this.text = text;
        this.appending = appending;
        this.prepending = prepending;
    }

    @Override
    public final String asString() {
        return new UncheckedText(
            new Joined(
                new TextOf("\n"),
                new Mapped<Text>(
                    line -> new TextOfMessageFormat(
                        new TextOf("{0}{1}{2}"),
                        this.prepending,
                        line,
                        this.appending
                    ),
                    new Sticky<Text>(
                        new Split(
                            this.text,
                            "\n"
                        )
                    )
                )
            )
        ).asString();
    }

}
