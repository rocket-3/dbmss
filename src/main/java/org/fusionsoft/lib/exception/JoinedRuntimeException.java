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

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The type of RuntimeException, constructed of joined by messages exceptions.
 * @since 0.1
 */
public class JoinedRuntimeException extends RuntimeException {

    /**
     * Instantiates a new JoinedRuntimeException.
     * @param causes Causes.
     */
    public JoinedRuntimeException(final Throwable... causes) {
        super(
            new UncheckedText(
                new Joined(
                    new TextOf(";\n"),
                    new Mapped<Text>(
                        cause -> new TextOf(cause.getMessage()),
                        new IterableOf<>(causes)
                    )
                )
            ).asString()
        );
    }

}
