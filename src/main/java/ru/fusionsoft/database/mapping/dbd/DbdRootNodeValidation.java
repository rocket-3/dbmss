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
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Proc;
import ru.fusionsoft.database.mapping.fields.DbdInfoFields;
import ru.fusionsoft.database.mapping.fields.DbdRootFields;
import ru.fusionsoft.lib.exception.ValidationException;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;

/**
 * The procedure of {@link YamlNode} that checks it to be DBD root mapping.
 * @since 0.1
 */
public class DbdRootNodeValidation implements Proc<YamlNode> {

    @Override
    public final void exec(final YamlNode input) {
        final YamlMapping mapping = input.asMapping();
        if (!"db".equals(
            new TextOfMappingValue(
                new DbdInfoMapping(
                    new YamlMappingOfPath(
                        mapping,
                        DbdRootFields.INFO
                    )
                ),
                DbdInfoFields.TYPE.asString()
            ).asString()
        )) {
            throw new ValidationException("document type is not DBD");
        }
    }

}
