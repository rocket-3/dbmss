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

import java.util.Set;
import org.cactoos.Text;
import org.cactoos.set.SetOf;

/**
 * The enum of fields of DBD/schemas/#schema/tables/#table mapping.
 */
public enum DbdTableFields implements Text {

    /**
     *The dbd/schemas/#schema node.
     */
    SCHEMA("schema"),
    /**
     *The dbd/schemas/#schema/tables/#table node.
     */
    TABLE("table"),
    /**
     *The dbd/schemas/#schema/tables/#table/owner node.
     */
    OWNER("owner"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    TABLESPACE("tablespace"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    DESCRIPTION("description"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    DEPENDENCIES("dependencies"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    PARTKEYDEFINITION("partkeydefinition"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    PARTKEYRANGE("partkeyrange"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    PARENT("parent"),
    /**
     *The dbd/schemas/#schema/tables/#table/columns node.
     */
    COLUMNS("columns"),
    /**
     *The dbd/schemas/#schema/tables/#table/indexes node.
     */
    INDEXES("indexes"),
    /**
     *The dbd/schemas/#schema/tables/#table/indexes node.
     */
    TRIGGERS("triggers"),
    /**
     *The dbd/schemas/#schema/tables/#table/constraints node.
     */
    CONSTRAINTS("constraints"),
    /**
     *The dbd/schemas/#schema/tables/#table/data node.
     */
    DATA("data");

    /**
     * The String encapsulated.
     */
    private final String value;

    /**
     * Instantiates a new DbdTableFields.
     * @param string The String to be encapsulated.
     */
    DbdTableFields(final String string) {
        this.value = string;
    }

    @Override
    public String asString() {
        return this.value;
    }

    /**
     * Necessary set.
     * @return The set.
     */
    @SuppressWarnings("PMD")
    public static Set<Text> necessary() {
        return new SetOf<>(COLUMNS);
    }

    /**
     * With data set.
     * @return The set.
     */
    @SuppressWarnings("PMD")
    public static Set<Text> all() {
        return new SetOf<>(DbdTableFields.values());
    }
}
