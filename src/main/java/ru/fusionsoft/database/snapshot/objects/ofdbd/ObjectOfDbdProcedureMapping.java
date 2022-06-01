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
import ru.fusionsoft.database.mapping.dbd.DbdProcedureMapping;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlScalarNode;

public class ObjectOfDbdProcedureMapping extends SimpleDbObject<DbdProcedureMapping> {

    /**
     * Instantiates a new Trigger of dbd mapping.
     * @param functions The {@link YamlMapping} to be encapsulated.
     * @param key The {@link YamlNode} to be encapsulated.
     * @param schema The {@link ObjectName} to be encapsulated.
     */
    public ObjectOfDbdProcedureMapping(
        final YamlMapping functions,
        final YamlNode key,
        final ObjectName schema
    ) {
        this(
            new DbdProcedureMapping(new YamlMappingOfPath(functions, key)),
            new SimpleObjectName(
                schema,
                new TextOfYamlScalarNode(key)
            )
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param yaml The {@link DbdProcedureMapping} to be encapsulated.
     * @param name The {@link ObjectName} to be encapsulated.
     */
    public ObjectOfDbdProcedureMapping(final DbdProcedureMapping yaml, final ObjectName name) {
        super(
            yaml,
            new SimpleObjectSignature(
                name,
                new ObjectTypeProcedure()
            )
        );
    }

}
