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
package org.fusionsoft.lib.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.TextOfString;

/**
 * The iterable of RegExp matches from {@link Text}.
 * @since 0.1
 */
public class IterableOfRegexpMatches extends IterableEnvelope<Text> {

    /**
     * Instantiates a new Iterable of regexp matches.
     * @param pattern The {@link Scalar} of {@link Pattern} to be encapsulated.
     * @param group The func for each group to be encapsulated.
     * @param origin The {@link Text} to be encapsulated.
     */
    public IterableOfRegexpMatches(
        final Scalar<Pattern> pattern,
        final Func<Matcher, String> group,
        final Text origin
    ) {
        super(
            new IterableOf<>(
                () -> {
                    final List<Text> matches = new ArrayList<>(0);
                    final Matcher matcher = pattern.value().matcher(origin.asString());
                    while (matcher.find()) {
                        matches.add(new TextOfString(group.apply(matcher)));
                    }
                    return matches.iterator();
                }
            )
        );
    }

    /**
     * Instantiates a new Iterable of regexp matches.
     * @param regexp The RegExp's {@link Text} to be encapsulated.
     * @param group The func for each group to be encapsulated.
     * @param origin The {@link Text} to be encapsulated.
     */
    public IterableOfRegexpMatches(
        final Text regexp,
        final Func<Matcher, String> group,
        final Text origin
    ) {
        this(
            () -> Pattern.compile(regexp.asString()),
            group,
            origin
        );
    }

}
