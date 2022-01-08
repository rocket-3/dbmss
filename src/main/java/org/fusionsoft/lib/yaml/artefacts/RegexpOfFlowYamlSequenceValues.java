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
package org.fusionsoft.lib.yaml.artefacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;

/**
 * The RegExp of yaml array sequence pattern and extract from matcher function.
 * @since 0.1
 */
public class RegexpOfFlowYamlSequenceValues {

    /**
     * The scalar of Pattern encapsulated.
     */
    private final Unchecked<Pattern> scalar;

    /**
     * The {@link Func} to extract string value from matcher.
     */
    private final UncheckedFunc<Matcher, String> func;

    /**
     * Instantiates a new Regexp of flow yaml sequence values.
     */
    public RegexpOfFlowYamlSequenceValues() {
        this.scalar = new Unchecked<>(
            new Sticky<>(
                () -> Pattern.compile(
                    "(?:\\s*(?:\"((?>[^\"]|[\"]{2})*)\"|([^,]+))\\s*,?|(?<=,),?)+?"
                )
            )
        );
        this.func = new UncheckedFunc<>(
            matcher -> new Ternary<>(
                () -> matcher.group(1) == null,
                () -> matcher.group(2),
                () -> matcher.group(1)
            ).value()
        );
    }

    /**
     * Pattern.
     * @return The pattern.
     */
    public final Pattern pattern() {
        return this.scalar.value();
    }

    /**
     * Extract string.
     * @param matcher The {@link Matcher}.
     * @return The string.
     */
    public final String extract(final Matcher matcher) {
        return this.func.apply(matcher);
    }

}
