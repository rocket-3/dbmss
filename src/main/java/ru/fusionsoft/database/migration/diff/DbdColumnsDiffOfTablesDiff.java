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
package ru.fusionsoft.database.migration.diff;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.text.DbdColumnIdentity;

/**
 * The {@link SmartIterableTemporalDiff} of given table objects {@link TemporalDiff} columns.
 * @since 0.1
 */
public class DbdColumnsDiffOfTablesDiff extends SmartIterableTemporalDiff<DbdColumnMapping> {

    /**
     * Instantiates a new Dbd columns diff of tables diff.
     * @param table The {@link TemporalDiff} of table {@link DbObject}s to be encapsulated.
     */
    public DbdColumnsDiffOfTablesDiff(final TemporalDiff<DbObject<YamlNode>> table) {
        super(
            DbdColumnIdentity::new,
            new DbdColumnsOfTable(table.current()),
            new DbdColumnsOfTable(table.next())
        );
    }

    /**
     * Changed iterable.
     * @return The iterable.
     */
    public final Iterable<TemporalDiff<DbdColumnMapping>> changed() {
        return this.changedBy(column -> column.asMapping().toString().hashCode());
    }

}
