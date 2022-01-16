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

import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/servers mapping that can be constructed of {@link DbdServerMapping}'s.
 * @since 0.1
 */
public class DbdServersMappingOfEntries extends DbdServersMapping {

    /**
     * Instantiates a new Dbd servers mapping.
     * @param entries The Iterable of DbdServerMapping to be encapsulated.
     */
    public DbdServersMappingOfEntries(final Iterable<Map.Entry<Text, DbdServerMapping>> entries) {
        super(new YamlMappingOfEntries(entries));
    }

    /**
     * Instantiates a new Dbd servers mapping.
     * @param entries The DbdServerMapping array to be encapsulated.
     */
    @SafeVarargs
    public DbdServersMappingOfEntries(final Map.Entry<Text, DbdServerMapping>... entries) {
        this(new IterableOf<>(entries));
    }

}
