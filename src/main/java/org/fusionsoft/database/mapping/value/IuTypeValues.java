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

package org.fusionsoft.database.mapping.value;

import org.cactoos.Text;

public enum IuTypeValues implements Text {
    NUMBER("number"),
    STRING("string"),
    DATE("date"),
    BOOLEAN("boolean"),
    BINARY("binary"),
    ARRAY("array"),
    JSON("json"),
    NATIVE("native");

    private final String text;

    IuTypeValues(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
