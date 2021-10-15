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
import org.cactoos.iterable.IterableOf;

/**
 * The enum of fields of DBD/schemas/#schema/tables/#table/columns/#column mapping.
 */
public enum DbdColumnFields implements Text {

    /**
     *Iucolumn dbd column fields.
     */
    IUCOLUMN("iuColumn"),
    /**
     *Dbname dbd column fields.
     */
    DBNAME("dbName"),
    /**
     *Type dbd column fields.
     */
    TYPE("type"),
    /**
     *Dbnullable dbd column fields.
     */
    DBNULLABLE("dbNullable"),
    /**
     *Description dbd column fields.
     */
    DESCRIPTION("description"),
    /**
     *Identity dbd column fields.
     */
    IDENTITY("dbLocalIdMethod"),
    /**
     *Iujsoncolumn dbd column fields.
     */
    IUJSONCOLUMN("iuJsonColumn"),
    /**
     *Iuinludeprops dbd column fields.
     */
    IUINLUDEPROPS("iuIncludeProps"),
    /**
     *Iuincluderest dbd column fields.
     */
    IUINCLUDEREST("iuIncludeRest"),
    /**
     * The String encapsulated.
     */
    DBMSTYPE("pgType");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new DbdColumnFields.
     * @param string The String to be encapsulated.
     */
    DbdColumnFields(final String string) {
        this.value = string;
    }

    @Override
    public final String asString() {
        return this.value;
    }

    /**
     * Identity fields of column data.
     * @return The set.
     */
    @SuppressWarnings("PMD")
    public static Iterable<DbdColumnFields> identity() {
        return new IterableOf<DbdColumnFields>(
            IUCOLUMN, IUJSONCOLUMN, DBNAME
        );
    }
}
