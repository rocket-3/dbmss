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
package org.fusionsoft.database.snapshot.objects.dbd;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.fusionsoft.database.mapping.fields.DbdSchemaFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.ObjectsEnvelope;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;
import org.fusionsoft.lib.yaml.artefacts.MappingFromMappingIgnoreKeys;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The type of db schema Objects of DBD/schemas/#schema node.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdSchemaMapping extends ObjectsEnvelope {

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
            new YamlMappingOfScalar(() -> parent.value(key).asMapping()),
            new TextOfScalarNode(key)
        );
    }

    /**
     * Instantiates a new Objects of dbd schema mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param key The Text to be encapsulated.
     */
    public ObjectsOfDbdSchemaMapping(
        final YamlMapping mapping,
        final Text key
    ) {
        super(
            new Joined<DbObject<? extends YamlMapping>>(
                new SimpleDbObject<>(
                    new MappingFromMappingIgnoreKeys(
                        mapping,
                        new IterableOf<>(DbdSchemaFields.TABLES)
                    ),
                    new SimpleObjectSignature(
                        new FullObjectName(key),
                        ObjectType.SCHEMA
                    )
                ),
                new ObjectsOfDbdTablesMapping(
                    new YamlMappingOfPath(
                        mapping,
                        DbdSchemaFields.TABLES.asString()
                    ),
                    key
                )
            )
        );
    }

}
