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
package ru.fusionsoft.database.mapping.values;

import org.cactoos.Text;

/**
 * The enum of all known DBMS constraints types.
 */
public enum ConstraintTypeValues implements Text {
    /**
     * Primary key.
     */
    PK("PK"),
    /**
     * Foreign key.
     */
    FK("FK"),
    /**
     * Not null.
     */
    NOT_NULL("NN"),
    /**
     * General check.
     */
    CHECK("CHECK"),
    /**
     * Unique constraint.
     */
    UNIQUE("UNIQUE"),
    /**
     * Exclude constraint.
     */
    EXCLUDE("EXCLUDE");

    /**
     * The String encapsulated.
     */
    private final String string;

    /**
     * Instantiates a new Constraint type values.
     * @param string The {@link String} to be encapsulated.
     */
    ConstraintTypeValues(final String string) {
        this.string = string;
    }

    @Override
    public String asString() {
        return this.string;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
