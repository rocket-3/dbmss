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
package org.fusionsoft.database.mapping.fields;

import java.util.Set;
import org.cactoos.Text;
import org.cactoos.set.SetOf;

/**
 * The enum of {@link org.fusionsoft.database.mapping.dbd.DbdSequenceMapping} fields.
 */
public enum DbdSequenceFields implements Text {
    /**
     *Schema dbd sequence fields.
     */
    SCHEMA("schema"),
    /**
     *Sequence dbd sequence fields.
     */
    SEQUENCE("sequence"),
    /**
     *Owner dbd sequence fields.
     */
    OWNER("owner"),
    /**
     *Start dbd sequence fields.
     */
    START("start"),
    /**
     *Min dbd sequence fields.
     */
    MIN("minimum"),
    /**
     *Max dbd sequence fields.
     */
    MAX("maximum"),
    /**
     *Increment dbd sequence fields.
     */
    INCREMENT("increment"),
    /**
     *Cycle dbd sequence fields.
     */
    CYCLE("cycle"),
    /**
     *Dependant table dbd sequence fields.
     */
    DEP_TABLE("dependant");

    /**
     * The String encapsulated.
     */
    private final String string;

    /**
     * Instantiates a new Dbd sequence fields.
     * @param string The String to be encapsulated.
     */
    DbdSequenceFields(final String string) {
        this.string = string;
    }

    @Override
    public String asString() {
        return this.string;
    }

    /**
     * Necessary set.
     * @return The set.
     */
    @SuppressWarnings("PMD.ProhibitPublicStaticMethods")
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
