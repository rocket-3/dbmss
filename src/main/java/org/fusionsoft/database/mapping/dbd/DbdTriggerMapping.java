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

import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.mapping.fields.DbdTriggerFields;
import org.fusionsoft.lib.yaml.YamlMappingHasKeys;

/**
 * The type of {@link YamlMappingHasKeys} of DBD/schemas/#schema/triggers/#trigger document node
 *  that are validated by {@link DbdTriggerFields}.
 * @since 0.1
 */
public class DbdTriggerMapping extends YamlMappingHasKeys {

    /**
     * Instantiates a new Dbd trigger mapping.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public DbdTriggerMapping(final YamlMapping mapping) {
        super(mapping, DbdTriggerFields.DDL);
    }

}
