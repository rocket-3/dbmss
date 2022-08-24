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

package ru.fusionsoft.database.snapshot.objects.sorted;

import java.util.Comparator;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Sorted;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.migration.order.OrderOfTableColumn;

/**
 * The name speaks for itself.
 * @since 0.1
 */
public class DbdColumnMappingsSortedByOrder extends IterableEnvelope<DbdColumnMapping> {

    /**
     * Ctor.
     * @param columns The wrapped columns
     */
    public DbdColumnMappingsSortedByOrder(final Iterable<DbdColumnMapping> columns) {
        super(
            new Sorted<>(
                Comparator.comparing(
                    column -> new OrderOfTableColumn(column).value()
                ),
                columns
            )
        );
    }

}
