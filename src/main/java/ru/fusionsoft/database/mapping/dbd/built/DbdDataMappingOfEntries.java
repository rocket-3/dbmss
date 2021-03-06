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
package ru.fusionsoft.database.mapping.dbd.built;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.dbd.DbdDataMapping;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link DbdDataMapping} with data: \n key: [value1, value2] \n form.
 * @since 0.1
 */
public class DbdDataMappingOfEntries extends DbdDataMapping {

    /**
     * Instantiates a new {@link DbdDataMapping}
     * of {@link Iterable}
     * of {@link Map.Entry}
     * of {@link Text} and {@link YamlNode}.
     * @param entries The YamlMapping to be encapsulated.
     */
    public DbdDataMappingOfEntries(final Iterable<Map.Entry<Text, YamlNode>> entries) {
        super(
            new YamlMappingOfEntries(
                new MapEntry<>(
                    DbdTableFields.DATA,
                    new YamlMappingOfEntries(entries)
                )
            )
        );
    }

}
