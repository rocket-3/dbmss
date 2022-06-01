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
package ru.fusionsoft.database.dbdreadable;

import ru.fusionsoft.database.DbdReadable;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.DbdServersMappingOfEntries;
import ru.fusionsoft.database.mapping.dbd.entry.DbdServersEntry;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

public class DbdReadableWithServerEntry extends DbdReadableMerged {

    public DbdReadableWithServerEntry(final DbdReadable original, final DbdServerEntry server) {
        super(
            original,
            new YamlMappingOfEntries(
                new DbdServersEntry(
                    new DbdServersMappingOfEntries(
                        server
                    )
                )
            )
        );
    }

}
