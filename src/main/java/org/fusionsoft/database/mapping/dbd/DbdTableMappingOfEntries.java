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
package org.fusionsoft.database.mapping.dbd;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.entries.SpareEntriesOfMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/sequences/#sequence/tables/#table node mapping.
 * @since 0.1
 */
public class DbdTableMappingOfEntries extends DbdTableMapping {

    /**
     * Instantiates a new Dbd table mapping of entries.
     * @param table The table DbObject entry to take data from.
     * @param indexes The Iterable of YamlMapping to be encapsulated.
     * @param constraints The Iterable of YamlMapping to be encapsulated.
     */
    public DbdTableMappingOfEntries(
        final DbObject<? extends YamlNode> table,
        final Iterable<MapEntry<Text, DbdIndexMapping>> indexes,
        final Iterable<MapEntry<Text, DbdConstraintMapping>> constraints
    ) {
        super(
            new YamlMappingOfEntries(
                new Joined<>(
                    new SpareEntriesOfMapping(
                        table,
                        DbdTableFields.withData()
                    ),
                    new IterableOf<MapEntry<? extends Text, ? extends YamlNode>>(
                        new MapEntry<>(
                            DbdTableFields.COLUMNS,
                            new DbdTableColumnsSequenceOf(table)
                        )
                    ),
                    new Filtered<MapEntry<? extends Text, ? extends YamlNode>>(
                        x -> x.getKey().asString().equals(
                            DbdTableFields.DATA.asString()
                        ),
                        new SpareEntriesOfMapping(
                            table,
                            DbdTableFields.necessary()
                        )
                    ),
                    new IterableOf<MapEntry<? extends Text, ? extends YamlNode>>(
                        new MapEntry<>(
                            DbdTableFields.CONSTRAINTS,
                            new YamlMappingOfEntries(constraints)
                        ),
                        new MapEntry<>(
                            DbdTableFields.INDEXES,
                            new YamlMappingOfEntries(indexes)
                        )
                    )
                )
            )
        );
    }

}
