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
package ru.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.mapping.fields.DbdSchemaFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.YamlMappingOfPathOrEmpty;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlScalarNode;

/**
 * The type of db schema Objects of DBD/schemas/#schema node.
 * @since 0.1
 * @todo #40:90min Implement parsing all of objects from DbdSchemaMapping
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdSchemaMapping extends ObjectsJoined {

    /**
     * Instantiates a new Objects of dbd schema mapping.
     * @param parent The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     */
    public ObjectsOfDbdSchemaMapping(
        final YamlMapping parent,
        final YamlNode key
    ) {
        this(
            new YamlMappingOfPath(
                parent,
                key
            ),
            new SimpleObjectName(
                new TextOfYamlScalarNode(key)
            )
        );
    }

    /**
     * Instantiates a new Objects of dbd schema mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param schema The {@link ObjectName} to be encapsulated.
     */
    public ObjectsOfDbdSchemaMapping(
        final YamlMapping mapping,
        final ObjectName schema
    ) {
        super(
            new IterableOf<DbObject<? extends YamlMapping>>(
                new SchemaOfDbdMapping(
                    mapping,
                    schema
                )
            ),
            new ObjectsOfDbdTablesMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.TABLES.asString()
                ),
                schema
            ),
            new ObjectsOfDbdSequencesMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.SEQUENCES.asString()
                ),
                schema
            ),
            new ObjectsOfDbdFunctionsMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.FUNCTIONS.asString()
                ),
                schema
            ),
            new ObjectsOfDbdProceduresMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.PROCEDURES.asString()
                ),
                schema
            ),
            new ObjectsOfDbdViewsMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.VIEWS.asString()
                ),
                schema
            ),
            new ObjectsOfDbdEnumsMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.ENUMS.asString()
                ),
                schema
            ),
            new ObjectsOfDbdDomainsMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.DOMAINS.asString()
                ),
                schema
            ),
            new ObjectsOfDbdTuplesMapping(
                new YamlMappingOfPathOrEmpty(
                    mapping,
                    DbdSchemaFields.TUPLES.asString()
                ),
                schema
            )
        );
    }

}
