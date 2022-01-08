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
package org.fusionsoft.database.mapping.dbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.mapping.fields.DbdSequenceFields;
import org.fusionsoft.lib.yaml.YamlMappingHasKeys;

/**
 * The type of {@link org.fusionsoft.lib.yaml.YamlMappingOf}
 *  of DBD/schemas/#schema/sequences/#sequence document node.
 * @since 0.1
 */
public class DbdSequenceMapping extends YamlMappingHasKeys {

    /**
     * Instantiates a new Dbd sequence mapping.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public DbdSequenceMapping(final YamlMapping mapping) {
        super(
            mapping,
            DbdSequenceFields.necessary()
        );
    }

}
