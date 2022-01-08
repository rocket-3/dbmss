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
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.mapping.fields.DbdServerFields;
import org.fusionsoft.lib.yaml.YamlMappingHasKeys;

/**
 * The mapping of DBD/server validated by having keys,
 *  when the keys are {@link DbdServerFields}.
 * @since 0.1
 */
public class DbdServerMapping extends YamlMappingHasKeys {

    /**
     * Instantiates a new Yaml mapping has keys.
     * @param mapping The YamlMapping to be used.
     */
    public DbdServerMapping(
        final YamlMapping mapping
    ) {
        super(
            mapping,
            new IterableOf<Text>(DbdServerFields.values())
        );
    }

}
