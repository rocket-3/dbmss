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

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.fields.DbdInfoFields;
import org.fusionsoft.lib.text.Empty;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlPlainScalarOf;

/**
 * The DbdInfoMapping that can be constructed of main attributes
 *  with default values.
 * @since 0.1
 */
public class DbdInfoMappingOf extends DbdInfoMapping {

    /**
     * Instantiates a new Dbd info mapping of.
     * @param title The Text to be encapsulated.
     * @param description The Text to be encapsulated.
     * @param version The Text to be encapsulated.
     */
    public DbdInfoMappingOf(
        final Text title,
        final Text description,
        final Text version
    ) {
        super(
            new YamlMappingOfEntries(
                new MapEntry<>(
                    DbdInfoFields.TYPE,
                    new YamlPlainScalarOf("db")
                ),
                new MapEntry<>(
                    DbdInfoFields.TITLE,
                    new YamlPlainScalarOf(title)
                ),
                new MapEntry<>(
                    DbdInfoFields.VERSION,
                    new YamlPlainScalarOf(version)
                ),
                new MapEntry<>(
                    DbdInfoFields.SUMMARY,
                    new YamlPlainScalarOf(new Empty())
                ),
                new MapEntry<>(
                    DbdInfoFields.DESCRIPTION,
                    new YamlPlainScalarOf(description)
                ),
                new MapEntry<>(
                    DbdInfoFields.TERMS,
                    new YamlPlainScalarOf(new Empty())
                ),
                new MapEntry<>(
                    DbdInfoFields.CONTACT,
                    new YamlPlainScalarOf(new Empty())
                ),
                new MapEntry<>(
                    DbdInfoFields.LICENSE,
                    new YamlPlainScalarOf(new Empty())
                )
            )
        );
    }

}
