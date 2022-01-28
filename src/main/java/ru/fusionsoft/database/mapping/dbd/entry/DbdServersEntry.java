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
package ru.fusionsoft.database.mapping.dbd.entry;

import ru.fusionsoft.database.mapping.dbd.DbdServersMapping;
import ru.fusionsoft.database.mapping.entries.YamlMappingEntryOfScalar;
import ru.fusionsoft.database.mapping.fields.DbdRootFields;

/**
 * The {@link DbdServersMapping} {@link java.util.Map.Entry} in DBD file.
 * @since 0.1
 */
public class DbdServersEntry extends YamlMappingEntryOfScalar<DbdServersMapping> {

    /**
     * Instantiates a new Dbd servers entry.
     * @param servers The {@link DbdServersMapping} to be encapsulated.
     */
    public DbdServersEntry(final DbdServersMapping servers) {
        super(
            DbdRootFields.SERVERS,
            () -> servers
        );
    }

}
