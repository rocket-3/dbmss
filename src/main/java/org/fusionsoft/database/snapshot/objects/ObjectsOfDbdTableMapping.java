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
package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.fusionsoft.database.mapping.fields.DdbTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.NaiveDbObject;
import org.fusionsoft.database.snapshot.ObjectType;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;
import org.fusionsoft.lib.yaml.artefacts.MappingFromMappingIgnoreKeys;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The type of db table Objects of DBD/schemas/#schema/tables/#table node.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdTableMapping extends ObjectsEnvelope {

    /**
     * Instantiates a new Objects of dbd table mapping.
     * @param parent The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param schema The Text to be encapsulated.
     */
    public ObjectsOfDbdTableMapping(
        final YamlMapping parent,
        final YamlNode key,
        final Text schema
    ) {
        this(
            new YamlMappingOfScalar(() -> parent.value(key).asMapping()),
            new FullObjectName(schema, new TextOfScalarNode(key))
        );
    }

    /**
     * Instantiates a new Objects of dbd schema mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param name The fully qualified name of table to be encapsulated.
     */
    public ObjectsOfDbdTableMapping(
        final YamlMapping mapping,
        final Text name
    ) {
        super(
            new Joined<>(
                new NaiveDbObject<>(
                    new MappingFromMappingIgnoreKeys(
                        mapping,
                        new IterableOf<>(
                            DdbTableFields.CONSTRAINTS,
                            DdbTableFields.INDEXES
                        )
                    ),
                    new SimpleObjectSignature(
                        new FullObjectName(name),
                        ObjectType.TABLE
                    )
                ),
                new Joined<DbObject<? extends YamlMapping>>(
                    new ObjectsOfDbdIndexesMapping(
                        new YamlMappingOfPath(
                            mapping,
                            DdbTableFields.INDEXES.asString()
                        ),
                        name
                    ),
                    new ObjectsOfDbdConstraintsMapping(
                        new YamlMappingOfPath(
                            mapping,
                            DdbTableFields.CONSTRAINTS.asString()
                        ),
                        name
                    )
                )
            )
        );
    }

}
