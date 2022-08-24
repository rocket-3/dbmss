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
import org.cactoos.scalar.Sticky;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The DBD/schemas/#schema/tables/#table/columns/# node mapping.
 * @since 0.1
 */
public class DbdColumnMapping extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping has keys.
     * @param mapping The YamlMapping to be used.
     */
    public DbdColumnMapping(final YamlMapping mapping) {
        super(
            new Sticky<>(
                () -> {
                    new DbdColumnValidation().exec(mapping);
                    return mapping;
                }
            )
        );
    }

}
