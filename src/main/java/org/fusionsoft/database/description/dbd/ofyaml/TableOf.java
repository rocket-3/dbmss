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
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.IOException;
import org.fusionsoft.database.description.dbd.SimpleTable;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

public class TableOf extends SimpleTable {

    private TableOf(final StrictYamlMapping mapping, final String name) {
        super(
            name,
            new IterableOfClassFromYamlNode<>(
                ColumnOf::new,
                mapping.yamlSequence("columns")
            ),
            new IterableOfClassFromYamlNode<>(
                ConstraintOf::new,
                mapping.yamlMapping("constraints")
            ),
            new IterableOfClassFromYamlNode<>(
                IndexOf::new,
                mapping.yamlMapping("indexes")
            )
        );
    }

    public TableOf(final YamlMapping containerMapping, final YamlNode keyNode) {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            keyNode.asScalar().value()
        );
    }

    public TableOf(final String name, final String text) throws IOException {
        this(
            new StrictYamlMapping(
                Yaml.createYamlInput(text)
                    .readYamlMapping()
            ),
            name
        );
    }

}
