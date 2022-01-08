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
package org.fusionsoft.lib.exception;

import java.text.MessageFormat;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Joined;
import org.cactoos.text.UncheckedText;

/**
 * The Text representation of {@link Map}.
 * @since 0.1
 */
public class TextOfMap implements Text {

    /**
     * The Map encapsulated.
     */
    private final Map<?, ?> map;

    /**
     * Instantiates a new Text of map.
     * @param map The Map to be encapsulated.
     */
    public TextOfMap(final Map<?, ?> map) {
        this.map = map;
    }

    @Override
    public final String asString() {
        return new UncheckedText(
            new Joined(
                ";\n",
                new Mapped<>(
                    entry -> MessageFormat.format(
                        "{0} : {1}",
                        entry.getKey(),
                        entry.getValue()
                    ),
                    this.map.entrySet()
                )
            )
        ).asString();
    }

}
