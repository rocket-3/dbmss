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

/**
 * The enum of fields of {@link org.fusionsoft.database.mapping.dbd.DbdDomainMapping}.
 */
public enum DbdDomainFields implements Text {
    /**
     *Schema dbd domain fields.
     */
    SCHEMA("schema"),
    /**
     *Domain dbd domain fields.
     */
    DOMAIN("domain"),
    /**
     *Type dbd domain fields.
     */
    TYPE("type"),
    /**
     *Default dbd domain fields.
     */
    DEFAULT("default"),
    /**
     *Onwer dbd domain fields.
     */
    ONWER("owner"),
    /**
     *Description dbd domain fields.
     */
    DESCRIPTION("description"),
    /**
     *Constraints dbd domain fields.
     */
    CONSTRAINTS("constraints");

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Dbd domain fields.
     * @param text The String to be encapsulated.
     */
    DbdDomainFields(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
