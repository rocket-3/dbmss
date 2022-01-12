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
import ru.fusionsoft.database.snapshot.objects.DefaultObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The type of db schema Objects of DBD/schemas/#schema node.
 * @since 0.1
 * @todo #40:90min Implement parsing all of objects from DbdSchemaMapping
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdSchemaMapping extends DefaultObjectsJoined {

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
                new TextOfScalarNode(key)
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
                new YamlMappingOfPath(
                    mapping,
                    DbdSchemaFields.TABLES.asString()
                ),
                schema
            )
        );
    }

}
