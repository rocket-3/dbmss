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
 *
 */
package org.fusionsoft.database.description.dbd.ofyaml;

import com.amihaiemil.eoyaml.StrictYamlMapping;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.description.dbd.SimpleConstraint;
import org.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOf;
import org.fusionsoft.lib.yaml.artefacts.StringSetOf;

public class ConstraintOf extends SimpleConstraint {

    private ConstraintOf(final StrictYamlMapping mapping, final Text key) throws Exception {
        super(
            new UncheckedText(key).asString(),
            new StringSetOf(mapping, "dbColumn"),
            mapping.string("dbConstraintType"),
            new MaybeEmptyTextOf(mapping, "dbRefSchema").asString(),
            new MaybeEmptyTextOf(mapping, "dbRefTable").asString(),
            new StringSetOf(mapping, "dbRefColumn"),
            new StringSetOf(mapping, "dbFKColumn"),
            new MaybeEmptyTextOf(mapping, "dbRefUpdate").asString(),
            new MaybeEmptyTextOf(mapping, "dbRefDelete").asString()
        );
    }

    public ConstraintOf(final YamlMapping containerMapping, final YamlNode keyNode) throws Exception {
        this(
            new StrictYamlMapping(containerMapping.yamlMapping(keyNode)),
            new TextOfScalar(() -> keyNode.asScalar().value())
        );
    }

}
