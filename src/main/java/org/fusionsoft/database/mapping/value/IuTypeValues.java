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
package org.fusionsoft.database.mapping.value;

import org.cactoos.Text;

/**
 * The enum of 'iuType' field values
 *  of {@link org.fusionsoft.database.mapping.fields.DbdColumnFields}.
 * @since 0.1
 * @todo #40:30min Refactor of class, split 'number' types.
 */
public enum IuTypeValues implements Text {
    /**
     *Number iu type values.
     */
    NUMBER("number"),
    /**
     *String iu type values.
     */
    STRING("string"),
    /**
     *Date iu type values.
     */
    DATE("date"),
    /**
     *Boolean iu type values.
     */
    BOOLEAN("boolean"),
    /**
     *Binary iu type values.
     */
    BINARY("binary"),
    /**
     *Array iu type values.
     */
    ARRAY("array"),
    /**
     *Json iu type values.
     */
    JSON("json"),
    /**
     *Native iu type values.
     */
    NATIVE("native");

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Iu type values.
     * @param text The String to be encapsulated.
     */
    IuTypeValues(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
