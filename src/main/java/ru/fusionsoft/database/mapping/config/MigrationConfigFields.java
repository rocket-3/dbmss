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
package ru.fusionsoft.database.mapping.config;

import org.cactoos.Text;

/**
 * The enum of MigrationConfig fields.
 */
public enum MigrationConfigFields implements Text {
    /**
     *Dbms types casts.
     */
    DBMS_TYPES_CASTS("typecasts");

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Migration config fields.
     * @param text The {@link String} to be encapsulated.
     */
    MigrationConfigFields(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
