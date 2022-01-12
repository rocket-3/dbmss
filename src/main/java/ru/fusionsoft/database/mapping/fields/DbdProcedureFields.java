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
package ru.fusionsoft.database.mapping.fields;

import org.cactoos.Text;

/**
 * The enum of DBD/schemas/#schema/procedures/#procedure document node fields.
 */
public enum DbdProcedureFields implements Text {
    /**
     *Schema dbd procedure fields.
     */
    SCHEMA("schema"),
    /**
     *Owner dbd procedure fields.
     */
    OWNER("owner"),
    /**
     *Procedure dbd procedure fields.
     */
    PROCEDURE("procedure"),
    /**
     *Arguments dbd procedure fields.
     */
    ARGUMENTS("arguments"),
    /**
     *Ddl dbd procedure fields.
     */
    DDL("ddl");

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Dbd procedure fields.
     * @param text The String to be encapsulated.
     */
    DbdProcedureFields(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
