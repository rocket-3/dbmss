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
package org.fusionsoft.database.mapping.dbd.built;

import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.mapping.dbd.DbdInfoMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdInfoFields;
import org.fusionsoft.lib.text.JsonEmptyText;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DbdInfoMapping that can be constructed of main attributes
 *  with default values.
 * @since 0.1
 */
public class SimpleDbdInfoMapping extends DbdInfoMapping {

    /**
     * Instantiates a new Dbd info mapping of.
     * @param title The Text to be encapsulated.
     * @param description The Text to be encapsulated.
     * @param version The Text to be encapsulated.
     */
    public SimpleDbdInfoMapping(
        final Text title,
        final Text description,
        final Text version
    ) {
        super(
            new YamlMappingOfEntries(
                new ScalarEntry(
                    DbdInfoFields.TYPE,
                    new TextOf("db")
                ),
                new ScalarEntry(
                    DbdInfoFields.TITLE,
                    title
                ),
                new ScalarEntry(
                    DbdInfoFields.VERSION,
                    version
                ),
                new ScalarEntry(
                    DbdInfoFields.SUMMARY,
                    new JsonEmptyText()
                ),
                new ScalarEntry(
                    DbdInfoFields.DESCRIPTION,
                    description
                ),
                new ScalarEntry(
                    DbdInfoFields.TERMS,
                    new JsonEmptyText()
                ),
                new ScalarEntry(
                    DbdInfoFields.CONTACT,
                    new JsonEmptyText()
                ),
                new ScalarEntry(
                    DbdInfoFields.LICENSE,
                    new JsonEmptyText()
                )
            )
        );
    }

}
