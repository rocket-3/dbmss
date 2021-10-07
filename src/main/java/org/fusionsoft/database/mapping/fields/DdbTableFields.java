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
 * The enum of fields of DBD/schemas/#schema/tables/#table mapping.
 */
public enum DdbTableFields implements Text {
    /**
     *Indexes ddb table fields.
     */
    INDEXES("indexes"),
    /**
     *Constraints ddb table fields.
     */
    CONSTRAINTS("constraints");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new Database info fields.
     * @param string The String to be encapsulated.
     */
    DdbTableFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }
}
