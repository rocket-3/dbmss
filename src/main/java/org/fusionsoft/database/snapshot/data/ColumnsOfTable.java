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
package org.fusionsoft.database.snapshot.data;

import java.util.Comparator;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sorted;
import org.cactoos.iterable.Sticky;
import org.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnMappingsOfTable;
import org.fusionsoft.database.snapshot.DbObject;

public class ColumnsOfTable extends IterableEnvelope<Column> {

    public ColumnsOfTable(final Iterable<DbdColumnMapping> cols) {
        super(
            new Sticky<>(
                new Sorted<>(
                    Comparator.comparing(x -> x.order().intValue()),
                    new Mapped<Column>(
                        ColumnOfDbdColumnMapping::new,
                        cols
                    )
                )
            )
        );
    }

    public ColumnsOfTable(final DbObject<DbdTableMapping> table) {
        this(
            new DbdColumnMappingsOfTable(
                table
            )
        );
    }

    public ColumnsOfTable(final DbdTableMapping mapping) {
        this(
            new DbdColumnMappingsOfTable(
                mapping
            )
        );
    }

}
