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
 * The enumeration of node DBD/servers.
 */
public enum DbdServerFields implements Text {
    /**
     *Url database credentials field.
     */
    DBTYPE("dbType"),
    /**
     *Url database credentials field.
     */
    DBVERSION("dbVersion"),
    /**
     *Url database credentials field.
     */
    URL("url"),
    /**
     *User database credentials field.
     */
    USER("user"),
    /**
     *Pwd database credentials field.
     */
    PWD("password"),
    /**
     *Vars database credentials field.
     */
    DESCRIPTION("description"),
    /**
     *Vars database credentials field.
     */
    VARS("variables");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new Database info fields.
     * @param string The String to be encapsulated.
     */
    DbdServerFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }
}
