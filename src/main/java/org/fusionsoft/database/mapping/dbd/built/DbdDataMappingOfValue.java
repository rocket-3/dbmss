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
package org.fusionsoft.database.mapping.dbd.built;

import org.cactoos.Text;
import org.fusionsoft.database.mapping.dbd.DbdDataMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link DbdDataMapping} with 'data: "scalarValue"' form.
 * @since 0.1
 */
public class DbdDataMappingOfValue extends DbdDataMapping {

    /**
     * Instantiates a new Dbd data mapping of {@link Text} value.
     * @param value The {@link Text} to be encapsulated.
     */
    public DbdDataMappingOfValue(final Text value) {
        super(
            new YamlMappingOfEntries(
                new ScalarEntry(
                    DbdTableFields.DATA,
                    value
                )
            )
        );
    }

}
