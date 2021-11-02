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

import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;

/**
 * The type of {@link Text} decorator that is escaped by some char sequence.
 * @since 0.1
 */
public class EscapedText extends TextEnvelope {

    /**
     * Ctor.
     * @param text Text representing the text value.
     * @param quotes The Text to be encapsulated.
     */
    public EscapedText(final Text text, final Text quotes) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format(
                    "{0}{1}{0}",
                    quotes.asString(),
                    text.asString()
                )
            )
        );
    }

}
