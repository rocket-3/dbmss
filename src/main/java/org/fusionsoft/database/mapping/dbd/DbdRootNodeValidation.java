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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.mapping.fields.DbdInfoFields;
import org.fusionsoft.lib.exception.ValidationException;
import org.fusionsoft.lib.yaml.YamlMappingHasKeys;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;

/**
 * The procedure of {@link YamlNode} that checks it to be DBD root mapping.
 * @since 0.1
 */
public class DbdRootNodeValidation implements Proc<YamlNode> {

    @Override
    public final void exec(final YamlNode input) throws Exception {
        if (!"db".equals(
            new TextOfMappingValue(
                new YamlMappingHasKeys(
                    new YamlMappingOfPath(
                        input.asMapping(),
                        "info"
                    ),
                    new IterableOf<Text>(DbdInfoFields.values())
                ),
                DbdInfoFields.TYPE.asString()
            ).asString()
        )) {
            throw new ValidationException("document type is not DBD");
        }
    }

}
