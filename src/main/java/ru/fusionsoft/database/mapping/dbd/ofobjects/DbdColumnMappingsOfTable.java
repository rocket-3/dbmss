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
package ru.fusionsoft.database.mapping.dbd.ofobjects;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.yaml.YamlNodeOfPath;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfYamlSequence;

/**
 * The {@link Iterable} of {@link DbdColumnMapping},
 *  constructed of artifacts with {@link DbdTableMapping}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbdColumnMappingsOfTable extends IterableEnvelope<DbdColumnMapping> {

    /**
     * Instantiates a new Dbd column mappings of table.
     * @param object The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public DbdColumnMappingsOfTable(final DbObject<DbdTableMapping> object) {
        this(new MappingOfRepresentative(object));
    }

    /**
     * Instantiates a new Dbd column mappings of table.
     * @param mapping The {@link YamlMapping} to be encapsulated.
     */
    private DbdColumnMappingsOfTable(final YamlMapping mapping) {
        this(new DbdTableMapping(mapping));
    }

    /**
     * Instantiates a new Dbd column mappings of table.
     * @param mapping The {@link DbdTableMapping} to be encapsulated.
     */
    public DbdColumnMappingsOfTable(final DbdTableMapping mapping) {
        this(
            new YamlNodeOfPath(mapping, DbdTableFields.COLUMNS)
        );
    }

    /**
     * Instantiates a new Dbd column mappings of table.
     * @param columns The table\columns mapping node to be encapsulated.
     */
    public DbdColumnMappingsOfTable(final YamlNode columns) {
        super(
            new IterableOf<>(
                new Ternary<>(
                    () -> columns.type().equals(Node.MAPPING),
                    () -> new Mapped<>(
                        column -> new DbdColumnMapping(column.asMapping()),
                        columns.asMapping().values()
                    ).iterator(),
                    () -> new IterableOfYamlSequence<>(
                        columns,
                        column -> new DbdColumnMapping(column.asMapping())
                    ).iterator()
                )
            )
        );
    }

}
