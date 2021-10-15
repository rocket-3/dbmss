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

import java.util.Set;
import org.cactoos.Text;
import org.cactoos.set.SetOf;

/**
 * The enum of fields of DBD/schemas/#schema/tables/#table/constraints/#constraint mapping.
 */
public enum DbdConstraintFields implements Text {
    /**
     *Type dbd constraint fields.
     */
    TYPE("dbConstraintType"),
    /**
     *Src pk col dbd constraint fields.
     */
    SRC_PK_COL("dbColumn"),
    /**
     *Src fk col dbd constraint fields.
     */
    SRC_FK_COL("dbFKColumn"),
    /**
     *Tgt schema dbd constraint fields.
     */
    TGT_SCHEMA("dbRefSchema"),
    /**
     *Tgt table dbd constraint fields.
     */
    TGT_TABLE("dbRefTable"),
    /**
     *Tgt col dbd constraint fields.
     */
    TGT_COL("dbRefColumn"),
    /**
     *On update dbd constraint fields.
     */
    ON_UPDATE("dbRefUpdate"),
    /**
     *On delete dbd constraint fields.
     */
    ON_DELETE("dbRefDelete");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new DbdConstraintFields.
     * @param string The String to be encapsulated.
     */
    DbdConstraintFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }

    /**
     * Primary set.
     * @return The set.
     */
    @SuppressWarnings("PMD")
    public static Set<DbdConstraintFields> primary() {
        return new SetOf<>(
            TYPE,
            SRC_PK_COL
        );
    }

    /**
     * Foreign set.
     * @return The set.
     */
    @SuppressWarnings("PMD")
    public static Set<DbdConstraintFields> foreign() {
        return new SetOf<>(
            TYPE,
            SRC_FK_COL,
            TGT_SCHEMA,
            TGT_TABLE,
            TGT_COL,
            ON_UPDATE,
            ON_DELETE
        );
    }
}
