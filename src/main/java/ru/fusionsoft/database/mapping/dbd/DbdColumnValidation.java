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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.lib.yaml.YamlMappingOf;
import ru.fusionsoft.lib.yaml.artefacts.FirstNotEmptyTextOf;
import ru.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOfYamlMapping;

/**
 * The validation of DBD/schemas/#schema/tables/#table/columns/# node.
 * @since 0.1
 */
public class DbdColumnValidation implements Proc<YamlNode> {

    @Override
    public final void exec(final YamlNode node) throws Exception {
        new FirstNotEmptyTextOf(
            new Mapped<Text>(
                key -> new MaybeEmptyTextOfYamlMapping(
                    new YamlMappingOf(node),
                    key
                ),
                DbdColumnFields.identity()
            )
        ).asString();
    }

}
