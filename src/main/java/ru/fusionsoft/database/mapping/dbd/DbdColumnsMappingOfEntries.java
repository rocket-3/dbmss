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
package ru.fusionsoft.database.mapping.dbd;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.text.DbdColumnIdentity;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/schemas/#schema/tables/#table/columns document none, built of entries.
 * @since 0.1
 */
public class DbdColumnsMappingOfEntries extends DbdColumnsMapping {

    /**
     * Instantiates a new Dbd columns mapping.
     * @param columns The {@link Iterable} of {@link DbdColumnMapping} to be encapsulated.
     */
    public DbdColumnsMappingOfEntries(final Iterable<DbdColumnMapping> columns) {
        super(
            new YamlMappingOfEntries(
                new Mapped<Map.Entry<? extends Text, ? extends YamlNode>>(
                    column -> new MapEntry<>(
                        new DbdColumnIdentity(column),
                        column.asMapping()
                    ),
                    columns
                )
            )
        );
    }

}
