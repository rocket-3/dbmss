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

import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of that can be constructed of.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class DbdTablesMappingOfObjects extends YamlMappingOfEntries {

    /**
     * Instantiates a new Dbd tables mapping of objects.
     * @param objects The all Objects to be encapsulated.
     * @param schema The parent schema DbObject to be encapsulated.
     * @todo #49:30min Implement ctor.
     */
    public DbdTablesMappingOfObjects(
        final Objects objects,
        final DbObject<?> schema
    ) {
        super();
    }

}
