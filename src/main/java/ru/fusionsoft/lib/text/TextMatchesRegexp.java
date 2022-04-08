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

import java.util.regex.Pattern;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.UncheckedText;

/**
 * The predicate of {@link Pattern} matches againts {@link Text}.
 * @since 0.1
 */
public class TextMatchesRegexp implements Func<Text, Boolean> {

    /**
     * The scalar of Pattern encapsulated.
     */
    private final Unchecked<Pattern> pattern;

    /**
     * Instantiates a new Text matches regex.
     * @param pattern The {@link Scalar} of {@link Pattern} to be encapsulated.
     */
    public TextMatchesRegexp(final Scalar<Pattern> pattern) {
        this.pattern = new Unchecked<>(
            new Sticky<>(pattern)
        );
    }

    /**
     * Instantiates a new Text matches regex.
     * @param pattern The {@link Text} to be encapsulated.
     */
    public TextMatchesRegexp(final Text pattern) {
        this(
            () -> Pattern.compile(pattern.asString())
        );
    }

    @Override
    public final Boolean apply(final Text input) {
        return this.pattern.value().matcher(
            new UncheckedText(input).asString()
        ).matches();
    }

}
