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

import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/servers mapping that can be constructed of {@link DatabaseInfo}'s.
 * @since 0.1
 */
public class DbdServersMapping extends YamlMappingOfEntries {

    /**
     * Instantiates a new Dbd servers mapping.
     * @param databases The Iterable of DatabaseInfo to be encapsulated.
     */
    public DbdServersMapping(final Iterable<DatabaseInfo> databases) {
        super(
            new Mapped<>(
                entry -> {
                    return new MapEntry<>(
                        new TextOf(entry.name()),
                        entry.asYaml()
                    );
                },
                databases
            )
        );
    }

}
