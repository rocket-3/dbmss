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
import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.time.UTC;

public class DateTextOfUTC extends TextEnvelope {

    public DateTextOfUTC(final UTC utc, final Text pattern) {
        super(
            new TextOf(
                () -> new SimpleDateFormat(pattern.asString()).format(
                    new Date(utc.millis())
                )
            )
        );
    }

    public DateTextOfUTC(final UTC utc) {
        this(
            utc,
            new TextOf("dd.MM.yyyy HH:mm.ss")
        );
    }

}
