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
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.yaml.YamlMappingOf;
import org.fusionsoft.lib.yaml.YamlNodeOfPath;
import org.fusionsoft.lib.yaml.YamlSequenceOfNodes;

/**
 * The ways to create DBD/schemas/#schema/tables/#table/columns/ node mapping.
 * @since 0.1
 */
public class DbdTableColumnsSequenceOf extends YamlSequenceOfNodes {

    /**
     * Instantiates a new Dbd table columns sequence.
     * @param columns The Iterable of DbdColumnMapping to be encapsulated.
     */
    public DbdTableColumnsSequenceOf(final Iterable<DbdColumnMapping> columns) {
        super(columns);
    }

    /**
     * Instantiates a new Dbd table columns sequence of.
     * @param table The table DbObject to be encapsulated.
     */
    public DbdTableColumnsSequenceOf(final DbObject<?> table) {
        this(
            new Mapped<DbdColumnMapping>(
                node -> new DbdColumnMapping(new YamlMappingOf(node)),
                () ->
                    new YamlNodeOfPath(
                        new MappingOfRepresentative(table),
                        DbdTableFields.COLUMNS
                    ).asSequence().values().iterator()
            )
        );
    }

}
