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

import org.cactoos.text.Concatenated;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The type of RuntimeException that's about error of some data validation.
 * @since 0.1
 */
public class ValidationException extends RuntimeException {

    /**
     * Instantiates a new Validation exception.
     * @param message The String to be encapsulated.
     */
    public ValidationException(final String message) {
        super(
            new UncheckedText(
                new Concatenated(
                    new TextOf("Validation error: "),
                    new TextOf(message)
                )).asString()
        );
    }

}
