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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.database.mapping.dbd.DbdSchemaMapping;
import ru.fusionsoft.database.mapping.fields.DbdSchemaFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.yaml.EntriesOfYamlMapping;
import ru.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The DBD/schemas/#schema mapping of {@link Iterable} of {@link DbObject}s.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbdSchemaMappingOfObjects extends DbdSchemaMapping {

    /**
     * Instantiates a new Yaml mapping envelope.
     * @param objects The {@link Iterable} of {@link DbObject}s to be used.
     * @param schema The {@link DbObject} of YamlNode to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> DbdSchemaMappingOfObjects(
        final Iterable<DbObject<Y>> objects,
        final DbObject<DbdSchemaMapping> schema
    ) {
        super(
            new MappingWithoutNullScalars(
                new YamlMappingOfEntries(
                    new Joined<>(
                        new EntriesOfYamlMapping(
                            new MappingOfRepresentative(schema)
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.TABLES,
                                new DbdTablesMappingOfObjects(objects, schema)
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.SEQUENCES,
                                new DbdSequencesMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.VIEWS,
                                new DbdViewsMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.FUNCTIONS,
                                new DbdFunctionsMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.PROCEDURES,
                                new DbdProceduresMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.DOMAINS,
                                new DbdDomainsMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.TUPLES,
                                new DbdTuplesMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        ),
                        new IterableOf<>(
                            new MapEntry<>(
                                DbdSchemaFields.ENUMS,
                                new DbdEnumsMappingOfObjects(
                                    objects,
                                    schema
                                )
                            )
                        )
                    )
                )
            )
        );
    }

}
