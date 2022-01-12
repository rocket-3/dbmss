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
import ru.fusionsoft.database.mapping.dbd.DbdTriggerMapping;

/**
 * The enum of {@link DbdTriggerMapping} fields.
 */
public enum DbdTriggerFields implements Text {
    /**
     *Schema dbd trigger fields.
     */
    SCHEMA("schema"),
    /**
     *Table dbd trigger fields.
     */
    TABLE("table"),
    /**
     *Trigger dbd trigger fields.
     */
    TRIGGER("trigger"),
    /**
     *Ddl dbd trigger fields.
     */
    DDL("ddl");

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * Instantiates a new Dbd trigger fields.
     * @param text The String to be encapsulated.
     */
    DbdTriggerFields(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }
}
