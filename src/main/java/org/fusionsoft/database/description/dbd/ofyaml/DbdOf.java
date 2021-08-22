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
package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.description.dbd.SimpleDBD;
import org.fusionsoft.database.yaml.DBDYamlInput;
import org.fusionsoft.lib.yaml.YamlMappingOf;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

public class DbdOf extends SimpleDBD {

    public DbdOf(final YamlMapping mapping) {
        super(
            new IterableOfClassFromYamlNode<>(
                ServerOf::new,
                new StrictYamlMapping(
                    mapping.yamlMapping("servers")
                )
            ),
            new IterableOfClassFromYamlNode<>(
                SchemaOf::new,
                new StrictYamlMapping(
                    mapping.yamlMapping("schemas")
                )
            )
        );
    }

    @SuppressWarnings("deprecation")
    public DbdOf(final DBDYamlInput yamlInput) {
        this(
            new StrictYamlMapping(
                new YamlMappingOf(
                    yamlInput
                )
            )
        );
    }

    //    public DbdOf(String string) {
    //        this(
    //            new DBDYamlInput(
    //                new YamlInputOf(string)
    //            )
    //        );
    //    }
}
