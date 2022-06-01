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
import org.cactoos.text.TextOf;

public class TextOfConditionsSpaced extends TextOfConditionsJoined {

    public TextOfConditionsSpaced(
        final Iterable<Map.Entry<Scalar<Boolean>, Scalar<Text>>> conditionals
    ) {
        super(new TextOf(" "), conditionals);
    }

    @SafeVarargs
    public TextOfConditionsSpaced(final Map.Entry<Scalar<Boolean>, Scalar<Text>>... conditionals) {
        super(new TextOf(" "), conditionals);
    }

}
