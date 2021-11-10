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
 * The enum of DBD/schemas/#schema mapping fields.
 */
public enum DbdSchemaFields implements Text {
    /**
     *The dbd/schema/owner field.
     */
    OWNER("owner"),
    /**
     *The dbd/schema/tables field.
     */
    TABLES("tables"),
    /**
     *The dbd/schema/sequences field.
     */
    SEQUENCES("sequences"),
    /**
     *The dbd/schema/views field.
     */
    VIEWS("views"),
    /**
     *The dbd/schema/functions field.
     */
    FUNCTIONS("functions"),
    /**
     *The dbd/schema/procedures field.
     */
    PROCEDURES("procedures"),
    /**
     *The dbd/schema/domains field.
     */
    DOMAINS("domains"),
    /**
     *The dbd/schema/tuples field.
     */
    TUPLES("tuples");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new DbdSchemaFields.
     * @param string The String to be encapsulated.
     */
    DbdSchemaFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }
}
