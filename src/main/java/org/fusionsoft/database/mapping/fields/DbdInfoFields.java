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
 * The enumeration of DBD/info fields.
 */
public enum DbdInfoFields implements Text {
    /**
     *Type of document field.
     */
    TYPE("type"),
    /**
     *Tide of document field.
     */
    TITLE("title"),
    /**
     *Version field.
     */
    VERSION("version"),
    /**
     *Summary description of document field.
     */
    SUMMARY("summary"),
    /**
     *Full description of document.
     */
    DESCRIPTION("description"),
    /**
     *Terms of service field.
     */
    TERMS("termsOfService"),
    /**
     *Contacts for authors of document field.
     */
    CONTACT("contact"),
    /**
     *The license of document field.
     */
    LICENSE("license");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new DBD/info fields.
     * @param string The String to be encapsulated.
     */
    DbdInfoFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }
}
