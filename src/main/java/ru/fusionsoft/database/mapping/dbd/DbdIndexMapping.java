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

import com.amihaiemil.eoyaml.YamlMapping;
import ru.fusionsoft.database.mapping.fields.DbdIndexFields;
import ru.fusionsoft.lib.yaml.YamlMappingHasKeys;

/**
 * The DBD/schemas/#schema/tables/#table/indexes/#index node mapping.
 * @since 0.1
 */
public class DbdIndexMapping extends YamlMappingHasKeys {

    /**
     * Instantiates a new Yaml mapping has keys.
     * @param mapping The YamlMapping to be used.
     */
    public DbdIndexMapping(final YamlMapping mapping) {
        super(mapping, DbdIndexFields.necessary());
    }

}
