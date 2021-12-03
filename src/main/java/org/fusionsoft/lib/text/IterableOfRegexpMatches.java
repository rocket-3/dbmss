/*
 * Copyright (C) 2018-2021 FusionSoft
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

public class IterableOfRegexpMatches extends IterableEnvelope<Text> {

    public IterableOfRegexpMatches(
        final Scalar<Pattern> pattern,
        final Func<Matcher, String> group,
        final Text origin
    ) {
        super(
            new IterableOf<>(
                () -> {
                    final List<Text> matches = new ArrayList<>();
                    final Matcher matcher = pattern.value().matcher(origin.asString());
                    while (matcher.find()) {
                        matches.add(new TextOfString(group.apply(matcher)));
                    }
                    return matches.iterator();
                }
            )
        );
    }

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