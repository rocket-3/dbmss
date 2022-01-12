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
package ru.fusionsoft.database.snapshot.objects.predicate;

import org.cactoos.Func;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.yaml.artefacts.KeysFromYamlNode;

/**
 * The Func of DbObject -> Boolean, that checks {@link DbObject} has 'data' node.
 * @since 0.1
 */
public class ObjectHasDataNodePredicate implements Func<DbObject<?>, Boolean> {

    @Override
    public final Boolean apply(final DbObject<?> input) {
        return new KeysFromYamlNode(input.asYaml()).contains(
            DbdTableFields.DATA.asString()
        );
    }

}
