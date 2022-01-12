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
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.mapping.fields.DbdRootFields;
import ru.fusionsoft.lib.yaml.YamlMappingEnvelope;
import ru.fusionsoft.lib.yaml.YamlMappingHasKeys;
import ru.fusionsoft.lib.yaml.YamlMappingOf;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;
import ru.fusionsoft.lib.yaml.YamlNodeValidated;

/**
 * The type of YamlMapping that is root of DBD document.
 * @since 0.1
 */
public class DbdRootMapping extends YamlMappingEnvelope {

    /**
     * Instantiates a new {@link DbdRootMapping}.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public DbdRootMapping(final YamlMapping mapping) {
        super(
            new YamlMappingHasKeys(
                new YamlMappingOf(
                    new YamlNodeValidated(
                        new DbdRootNodeValidation(),
                        mapping
                    )
                ),
                new IterableOf<Text>(DbdRootFields.values())
            )
        );
    }

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public DbdRootMapping(final Scalar<DbdRootMapping> mapping) {
        super(new YamlMappingOfScalar(mapping));
    }

}
