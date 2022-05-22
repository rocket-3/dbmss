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
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The type of db table Objects of DBD/schemas/#schema/tables/#table node.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ObjectsOfDbdTableMapping extends ObjectsJoined {

    /**
     * Instantiates a new Objects of dbd table mapping.
     * @param parent The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param schema The Text to be encapsulated.
     */
    public ObjectsOfDbdTableMapping(
        final YamlMapping parent,
        final YamlNode key,
        final ObjectName schema
    ) {
        this(
            new YamlMappingOfPath(
                parent,
                key
            ),
            new SimpleObjectName(
                schema,
                new TextOfScalarNode(key)
            )
        );
    }

    /**
     * Instantiates a new Objects of dbd schema mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param table The fully qualified {@link ObjectName} of table to be encapsulated.
     */
    public ObjectsOfDbdTableMapping(
        final YamlMapping mapping,
        final ObjectName table
    ) {
        super(
            new IterableOf<>(
                new TableObjectOfDbdTableMapping(mapping, table)
            ),
            new IndexObjectsOfDbdTableMapping(
                mapping,
                DbdTableFields.INDEXES,
                table
            ),
            new ConstraintObjectsOfDbdTableMapping(
                mapping,
                DbdTableFields.CONSTRAINTS,
                table
            ),
            new TriggerObjectsOfDbdTableMapping(
                mapping,
                DbdTableFields.TRIGGERS,
                table
            ),
            new DataObjectsOfDbdTableMapping(
                mapping,
                DbdTableFields.DATA,
                table
            )
        );
    }

}
