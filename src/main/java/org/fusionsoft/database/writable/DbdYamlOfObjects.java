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
package org.fusionsoft.database.writable;

import org.cactoos.text.TextOf;
import org.fusionsoft.database.BaseYamlRepresentative;
import org.fusionsoft.database.Writable;
import org.fusionsoft.database.mapping.MappingEmpty;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.database.snapshot.DbObjects;

/**
 * The type of {@link Writable} representing Dbd yaml file,
 *  describing {@link DbObjects}.
 * @since 0.1
 * @todo #40:60min Implement `DbdYamlOfObjects` `Writable`
 */
@SuppressWarnings("PMD")
public class DbdYamlOfObjects extends WritableYamlDocument {

    /**
     * Instantiates a new Dbd yaml of objects.
     * @param database The DatabaseInfo to be encapsulated.
     * @param objects The DbObjects to be encapsulated.
     */
    public DbdYamlOfObjects(
        final DatabaseInfo database,
        final DbObjects objects
    ) {
        super(
            new BaseYamlRepresentative(new MappingEmpty()),
            new TextOf("DBD.yaml")
        );
    }

}