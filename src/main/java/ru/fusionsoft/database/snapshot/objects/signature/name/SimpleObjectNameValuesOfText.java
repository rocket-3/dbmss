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
package ru.fusionsoft.database.snapshot.objects.signature.name;

import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.text.Split;
import ru.fusionsoft.lib.text.RegexpLiteralEscaped;

/**
 * The iterable of names, splitted by {@link SimpleObjectNameDelimiter} of {@link Text}.
 * @since 0.1
 */
public class SimpleObjectNameValuesOfText extends IterableEnvelope<Text> {

    /**
     * Instantiates a new Simple object name values of text.
     * @param text The {@link Text} to be encapsulated.
     */
    public SimpleObjectNameValuesOfText(final Text text) {
        super(
            new Split(
                text,
                new RegexpLiteralEscaped(
                    new SimpleObjectNameDelimiter()
                )
            )
        );
    }

}
