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
import org.fusionsoft.lib.yaml.artefacts.FirstNotEmptyTextOf;

public class SimpleColumn implements Column {

    private final String name;

    private final String iuColumn;

    private final String iuJsonColumn;

    private final Set<String> iuIncludeProps;

    private final String dbName;

    private final String dbType;

    private final String type;

    private final boolean nullable;

    private final String description;

    private final String dbLocalIdMethod;

    public SimpleColumn(final String iuColumn, final String iuJsonColumn, final String type, final boolean nullable, final String description, final Set<String> iuIncludeProps, final String dbName, final String dbType, final String dbLocalIdMethod) {
        try {
            this.name = new FirstNotEmptyTextOf(
                dbName,
                iuColumn,
                iuJsonColumn
            ).asString();
            this.iuJsonColumn = iuJsonColumn;
            this.iuIncludeProps = iuIncludeProps;
            this.dbType = dbType;
            this.iuColumn = iuColumn;
            this.dbName = dbName;
            this.type = type;
            this.nullable = nullable;
            this.description = description;
            this.dbLocalIdMethod = dbLocalIdMethod;
        } catch (final Exception e) {
            throw new RuntimeException(
                "Unable to construct an object when all "
                + "'iuColumn', 'iuJsonColumn' and 'dbName' are empty",
                e
            );
        }
    }

    @Override
    public final String name() {
        return this.name;
    }

    @Override
    public final String iuColumn() {
        return this.iuColumn;
    }

    @Override
    public final String iuJsonColumn() {
        return this.iuJsonColumn;
    }

    @Override
    public final Set<String> iuIncludeProps() {
        return this.iuIncludeProps;
    }

    @Override
    public final String dbType() {
        return this.dbType;
    }

    @Override
    public final String dbName() {
        return this.dbName;
    }

    @Override
    public final String type() {
        return this.type;
    }

    @Override
    public final boolean nullable() {
        return this.nullable;
    }

    @Override
    public final String description() {
        return this.description;
    }

    @Override
    public final String dbLocalIdMethod() {
        return this.dbLocalIdMethod;
    }

}
