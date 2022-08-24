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
package ru.fusionsoft.database.migration.order;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.map.MapEnvelope;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.text.DbdColumnIdentity;

/**
 * Map {@link DbdColumnIdentity} to Integer of table DbObject
 *  or Iterable of {@link DbdColumnMapping}.
 * @since 0.1
 */
public class OrdersOfColumnsOfTable extends MapEnvelope<DbdColumnIdentity, Integer> {

    /**
     * Ctor.
     * @param cols The {@link DbdColumnMapping} iterable.
     */
    public OrdersOfColumnsOfTable(final Iterable<DbdColumnMapping> cols) {
        super(
            new MapOf<DbdColumnIdentity, Integer>(
                DbdColumnIdentity::new,
                entry -> new OrderOfTableColumn(entry).value(),
                cols
            )
        );
    }

    /**
     * Instantiates a new Orders of columns of table.
     * @param table The table {@link DbObject}.
     */
    public OrdersOfColumnsOfTable(final DbObject<YamlNode> table) {
        this(
            new DbdColumnsOfTable(table)
        );
    }

}
