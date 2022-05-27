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
package ru.fusionsoft.database.text;

import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Joined;
import org.cactoos.text.UncheckedText;

public class TextOfConditionsJoined implements Text {

    private final Iterable<Map.Entry<Scalar<Boolean>, Scalar<Text>>> conditionals;

    private final Text separator;

    public TextOfConditionsJoined(
        final Text separator,
        final Iterable<Map.Entry<Scalar<Boolean>, Scalar<Text>>> conditionals
    ) {
        this.separator = separator;
        this.conditionals = conditionals;
    }

    @SafeVarargs
    public TextOfConditionsJoined(
        final Text separator,
        final Map.Entry<Scalar<Boolean>, Scalar<Text>>... conditionals
    ) {
        this(
            separator,
            new IterableOf<>(conditionals)
        );
    }

    @Override
    public String asString() {
        return new UncheckedText(
            new Joined(
                this.separator,
                new Mapped<Text>(
                    entry -> entry.getValue().value(),
                    new Filtered<>(
                        conditional -> conditional.getKey().value(),
                        this.conditionals
                    )
                )
            )
        ).asString();
    }

}
