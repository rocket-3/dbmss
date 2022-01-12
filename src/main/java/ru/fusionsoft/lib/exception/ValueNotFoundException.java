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
package ru.fusionsoft.lib.exception;

import java.text.MessageFormat;

/**
 * The type of RuntimeException that means value for key in associative mapping
 * was not found.
 * @since 0.1
 */
public class ValueNotFoundException extends RuntimeException {

    /**
     * Instantiates a new ValueNotFoundException.
     * @param key The key for which value was not found.
     * @param mapping The CharSequence to be encapsulated.
     */
    public ValueNotFoundException(
        final CharSequence key,
        final CharSequence mapping
    ) {
        super(MessageFormat.format(
            "No value found for key `{0}` in mapping: \n- - - -\n{1}\n- - - -",
            key.toString(),
            mapping.toString()
        ));
    }

}
