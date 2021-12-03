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
package org.fusionsoft.lib.yaml.artefacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;

public class RegexpOfFlowYamlSequenceValues {

    private final Unchecked<Pattern> scalar;

    public RegexpOfFlowYamlSequenceValues() {
        this.scalar = new Unchecked<>(
            new Sticky<>(
                () -> Pattern.compile(
                    "(?:\\s*(?:\"((?>[^\"]|[\"]{2})*)\"|([^,]+))\\s*,?|(?<=,),?)+?"
                )
            )
        );
    }

    public final Pattern pattern() {
        return scalar.value();
    }

    public final String extract(final Matcher matcher) {
        return new Unchecked<>(
            new Ternary<>(
                () -> matcher.group(1) == null,
                () -> matcher.group(2),
                () -> matcher.group(1)
            )
        ).value();
    }

}
