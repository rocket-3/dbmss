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
import org.cactoos.iterable.IterableOf;

/**
 * The enum of DBD/schemas/#schema/tables/#tables/indexes/#index fields.
 */
public enum DbdIndexFields implements Text {
    /**
     *Schema dbd index fields.
     */
    TABLESPACE("tablespace"),
    /**
     *Schema dbd index fields.
     */
    SCHEMA("schema"),
    /**
     *Table dbd index fields.
     */
    TABLE("table"),
    /**
     *Index dbd index fields.
     */
    INDEX("index"),
    /**
     *Dbcolumn dbd index fields.
     */
    DBCOLUMN("dbColumn"),
    /**
     *Dbunique dbd index fields.
     */
    DBUNIQUE("dbUnique"),
    /**
     *Dbmstype dbd index fields.
     */
    DBMSTYPE("pgIndexType"),
    /**
     *Dbmstype dbd index fields.
     */
    OWNER("owner"),
    /**
     *Dbmstype dbd index fields.
     */
    DDL("ddl");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new DBD/schemas/#schema/tables/#table/indexes/# fields.
     * @param string The String to be encapsulated.
     */
    DbdIndexFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }

    /**
     * Necessary iterable.
     * @return The iterable.
     */
    @SuppressWarnings("PMD")
    public static Iterable<Text> necessary() {
        return new IterableOf<Text>(DBCOLUMN, DBUNIQUE);
    }
}
