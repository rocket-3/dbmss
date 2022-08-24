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

import org.cactoos.Scalar;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * Extracting column order of given {@link DbdColumnMapping}.
 * @since 0.1
 */
public class OrderOfTableColumn implements Scalar<Integer> {

    /**
     * The {@link DbdColumnMapping} encapsulated.
     */
    private final DbdColumnMapping mapping;

    /**
     * Instantiates a new Order of table column.
     * @param mapping The {@link DbdColumnMapping} to be encapsulated.
     */
    public OrderOfTableColumn(final DbdColumnMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public final Integer value() {
        return Integer.valueOf(
            new TextOfYamlMappingKeyValue(this.mapping, DbdColumnFields.ORDER).asString()
        );
    }

}
