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
package org.fusionsoft.database.description.dbd;

import java.util.Set;

/**
 * Unfinished JavaDoc.
 * The type is still in pre-design stage.
 * @since 0.0.1
 * @todo Make right Javadoc after re-design
 * @checkstyle JavadocTagsCheck (4096 lines)
 * @checkstyle JavadocLocationCheck (4096 lines)
 * @checkstyle JavadocMethodCheck (4096 lines)
 * @checkstyle RegexpSinglelineCheck (4096 lines)
 * @checkstyle JavadocVariableCheck (4096 lines)
 * @checkstyle MemberNameCheck (4096 lines)
 * @checkstyle ParameterNameCheck (4096 lines)
 * @checkstyle ParameterNumberCheck (4096 lines)
 * @checkstyle StringLiteralsConcatenationCheck (4096 lines)
 * @checkstyle AbbreviationAsWordInNameCheck (4096 lines)
 * @checkstyle LineLengthCheck (4096 lines)
 * @checkstyle AvoidFieldNameMatchingMethodName (4096 lines)
 */
@SuppressWarnings("PMD")
public class SimpleConstraint implements Constraint {

    private final String key;

    private final Set<String> dbColumn;

    private final String dbConstraintType;

    private final String dbRefSchema;

    private final String dbRefTable;

    private final Set<String> dbRefColumn;

    private final Set<String> dbFKColumn;

    private final String dbRefUpdate;

    private final String dbRefDelete;

    public SimpleConstraint(final String key, final Set<String> dbColumn, final String dbConstraintType, final String dbRefSchema, final String dbRefTable, final Set<String> dbRefColumn, final Set<String> dbFKColumn, final String dbRefUpdate, final String dbRefDelete) {
        this.key = key;
        this.dbColumn = dbColumn;
        this.dbConstraintType = dbConstraintType;
        this.dbRefSchema = dbRefSchema;
        this.dbRefTable = dbRefTable;
        this.dbRefColumn = dbRefColumn;
        this.dbFKColumn = dbFKColumn;
        this.dbRefUpdate = dbRefUpdate;
        this.dbRefDelete = dbRefDelete;
    }

    @Override
    public final String key() {
        return this.key;
    }

    @Override
    public final Set<String> dbColumn() {
        return this.dbColumn;
    }

    @Override
    public final String dbConstraintType() {
        return this.dbConstraintType;
    }

    @Override
    public final Set<String> dbFKColumn() {
        return this.dbFKColumn;
    }

    @Override
    public final String dbRefSchema() {
        return this.dbRefSchema;
    }

    @Override
    public final String dbRefTable() {
        return this.dbRefTable;
    }

    @Override
    public final Set<String> dbRefColumn() {
        return this.dbRefColumn;
    }

    @Override
    public final String dbRefUpdate() {
        return this.dbRefUpdate;
    }

    @Override
    public final String dbRefDelete() {
        return this.dbRefDelete;
    }

}
