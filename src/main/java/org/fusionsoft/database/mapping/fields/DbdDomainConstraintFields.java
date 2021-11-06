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

import org.cactoos.Text;
import org.fusionsoft.database.mapping.dbd.DbdDomainConstraintMapping;

/**
 * The enum of {@link DbdDomainConstraintMapping}'s fields.
 */
public enum DbdDomainConstraintFields implements Text {

    /**
     *Schema dbd domain constraint fields.
     */
    SCHEMA("schema"),
    /**
     *Domain dbd domain constraint fields.
     */
    DOMAIN("domain"),
    /**
     *Constraint dbd domain constraint fields.
     */
    CONSTRAINT("constraint"),
    /**
     *Validated dbd domain constraint fields.
     */
    VALIDATED("validated"),
    /**
     *Condition dbd domain constraint fields.
     */
    CONDITION("condition");

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Dbd domain constraint fields.
     * @param text The String to be encapsulated.
     */
    DbdDomainConstraintFields(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
