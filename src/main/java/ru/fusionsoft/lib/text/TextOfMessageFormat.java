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

import java.text.MessageFormat;
import java.util.List;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The {@link Text} constructed of {@link MessageFormat}.
 * @since 0.1
 */
public class TextOfMessageFormat implements Text {

    /**
     * The {@link Text} of pattern encapsulated.
     */
    private final UncheckedText pattern;

    /**
     * The {@link org.cactoos.Scalar} of {@link List} of {@link String} encapsulated.
     */
    private final Unchecked<List<String>> args;

    /**
     * Instantiates a new Text message format.
     * @param pattern The Text of pattern to be encapsulated.
     * @param args The Iterable of Text of arguments to be encapsulated.
     */
    public TextOfMessageFormat(final Text pattern, final Iterable<Text> args) {
        this.pattern = new UncheckedText(pattern);
        this.args = new Unchecked<>(
            new Sticky<>(
                () -> new ListOf<>(new Mapped<>(Text::asString, args))
            )
        );
    }

    /**
     * Instantiates a new Text message format.
     * @param pattern The {@link Text} of pattern to be encapsulated.
     * @param args The {@link Text} array of args to be encapsulated.
     */
    public TextOfMessageFormat(final Text pattern, final Text... args) {
        this(
            pattern,
            new ListOf<>(args)
        );
    }

    /**
     * Instantiates a new Text message format.
     * @param pattern The {@link Text} of pattern to be encapsulated.
     * @param args The {@link Text} array of args to be encapsulated.
     */
    public TextOfMessageFormat(final CharSequence pattern, final CharSequence... args) {
        this(
            new TextOf(pattern),
            new Mapped<Text>(TextOf::new, args)
        );
    }

    @Override
    public final String asString() {
        return MessageFormat.format(
            this.pattern.asString(),
            this.args.value().toArray()
        );
    }

}
