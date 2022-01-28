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
package ru.fusionsoft.database.text;

import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.lib.yaml.artefacts.FirstNotEmptyTextOf;
import ru.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOfYamlMapping;

/**
 * The unique identity of {@link DbdColumnMapping} {@link Text}.
 * @since 0.1
 */
public class DbdColumnIdentity extends FirstNotEmptyTextOf {

    /**
     * Instantiates a new Dbd column identity.
     * @param column The {@link DbdColumnMapping} to be encapsulated.
     */
    public DbdColumnIdentity(final DbdColumnMapping column) {
        super(
            new Mapped<Text>(
                key -> new MaybeEmptyTextOfYamlMapping(
                    column,
                    key
                ),
                DbdColumnFields.identity()
            )
        );
    }

}
