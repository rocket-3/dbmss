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
package org.fusionsoft.database.mapping;

import org.fusionsoft.database.YamlRepresentative;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The YamlMapping of {@link DbObject} YamlRepresentative.
 * @since 0.1
 */
public class MappingOfRepresentative extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping of scalar.
     * @param representative The Scalar of YamlMapping to be encapsulated.
     */
    public MappingOfRepresentative(
        final YamlRepresentative<?> representative
    ) {
        super(() -> representative.asYaml().asMapping());
    }

}
