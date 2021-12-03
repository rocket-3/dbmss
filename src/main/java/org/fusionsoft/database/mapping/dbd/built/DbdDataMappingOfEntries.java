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
package org.fusionsoft.database.mapping.dbd.built;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.dbd.DbdDataMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

public class DbdDataMappingOfEntries extends DbdDataMapping {

    /**
     * Instantiates a new Yaml entries envelope.
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
