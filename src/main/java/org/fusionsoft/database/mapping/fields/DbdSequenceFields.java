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
package org.fusionsoft.database.mapping.fields;

import java.util.Set;
import org.cactoos.Text;
import org.cactoos.set.SetOf;

public enum DbdSequenceFields implements Text {
    SCHEMA("schema"),
    SEQUENCE("sequence"),
    OWNER("owner"),
    START("start"),
    MIN("minimum"),
    MAX("maximum"),
    INCREMENT("increment"),
    CYCLE("cycle"),
    DEP_TABLE("dependant");

    private final String string;

    DbdSequenceFields(final String string) {
        this.string = string;
    }

    @Override
    public String asString() {
        return this.string;
    }

    public static Set<DbdSequenceFields> necessary() {
        return new SetOf<DbdSequenceFields>(
            OWNER,
            START,
            MIN,
            MAX,
            INCREMENT,
            CYCLE
        );
    }
}
