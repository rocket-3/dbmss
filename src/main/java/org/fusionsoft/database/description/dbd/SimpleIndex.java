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

public class SimpleIndex implements Index {

    private final String key;

    private final String dbColumn;

    private final boolean dbUnique;

    private final String indexType;

    public SimpleIndex(final String key, final String dbColumn, final boolean dbUnique, final String indexType) {
        this.key = key;
        this.dbColumn = dbColumn;
        this.dbUnique = dbUnique;
        this.indexType = indexType;
    }

    @Override
    public final String key() {
        return this.key;
    }

    @Override
    public final String dbColumn() {
        return this.dbColumn;
    }

    @Override
    public final boolean dbUnique() {
        return this.dbUnique;
    }

    @Override
    public final String indexType() {
        return this.indexType;
    }

}
