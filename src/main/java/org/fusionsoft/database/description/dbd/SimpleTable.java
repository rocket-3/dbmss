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

import java.util.Collection;
import java.util.Collections;
import org.fusionsoft.database.StringProperty;

public class SimpleTable implements Table {

    private final String key;

    private final Iterable<Column> columns;

    private final Iterable<Constraint> constraints;

    private final Iterable<Index> indexes;

    public SimpleTable(final String key, final Iterable<Column> columns, final Iterable<Constraint> constraints, final Iterable<Index> indexes) {
        this.key = key;
        this.columns = columns;
        this.constraints = constraints;
        this.indexes = indexes;
    }

    @Override
    public final String key() {
        return this.key;
    }

    @Override
    public final Iterable<Column> columns() {
        return this.columns;
    }

    @Override
    public final Iterable<Constraint> constraints() {
        return this.constraints;
    }

    @Override
    public final Iterable<Index> indexes() {
        return this.indexes;
    }

    @Override
    public Collection<StringProperty> dmbsProps() {
        return Collections.emptySet();
    }

}
