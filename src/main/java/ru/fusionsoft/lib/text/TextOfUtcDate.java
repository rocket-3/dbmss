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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.time.Utc;

/**
 * The date {@link Text} of {@link Utc}.
 * @since 0.1
 */
public class TextOfUtcDate extends TextEnvelope {

    /**
     * Instantiates a new Date text of utc.
     * @param utc The {@link Utc} to be encapsulated.
     * @param pattern The {@link Text} to be encapsulated.
     */
    public TextOfUtcDate(final Utc utc, final Text pattern) {
        super(
            new TextOf(
                () -> new SimpleDateFormat(pattern.asString(), Locale.ENGLISH).format(
                    new Date(utc.millis())
                )
            )
        );
    }

    /**
     * Instantiates a new Date text of utc.
     * @param utc The {@link Utc} to be encapsulated.
     */
    public TextOfUtcDate(final Utc utc) {
        this(
            utc,
            new TextOf("dd.MM.yyyy HH:mm.ss")
        );
    }

}
