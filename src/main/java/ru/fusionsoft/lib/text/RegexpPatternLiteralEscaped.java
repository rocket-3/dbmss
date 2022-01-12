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
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;

/**
 * The Regexp pattern of text being escaped for matching.
 * @since 0.1
 */
public class RegexpPatternLiteralEscaped extends TextEnvelope {

    /**
     * Ctor.
     * @param text Text to be quoted.
     */
    public RegexpPatternLiteralEscaped(final Text text) {
        super(
            new TextOfScalar(
                () -> text.asString().replaceAll("[\\W]", "\\\\$0")
            )
        );
    }

    /**
     * Ctor.
     * @param text Text to be quoted.
     */
    public RegexpPatternLiteralEscaped(final CharSequence text) {
        this(new TextOf(text));
    }

}
