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
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeDomain;
import ru.fusionsoft.lib.yaml.YamlMappingOfPath;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlScalarNode;

public class ObjectOfDbdDomainMapping extends SimpleDbObject<DbdDomainMapping> {

    /**
     * Instantiates a new Trigger of dbd mapping.
     * @param domains The {@link YamlMapping} to be encapsulated.
     * @param key The {@link YamlNode} to be encapsulated.
     * @param schema The {@link ObjectName} to be encapsulated.
     */
    public ObjectOfDbdDomainMapping(
        final YamlMapping domains,
        final YamlNode key,
        final ObjectName schema
    ) {
        this(
            new DbdDomainMapping(new YamlMappingOfPath(domains, key)),
            new SimpleObjectName(
                schema,
                new TextOfYamlScalarNode(key)
            )
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param yaml The {@link DbdDomainMapping} to be encapsulated.
     * @param name The {@link ObjectName} to be encapsulated.
     */
    public ObjectOfDbdDomainMapping(final DbdDomainMapping yaml, final ObjectName name) {
        super(
            yaml,
            new SimpleObjectSignature(
                name,
                new ObjectTypeDomain()
            )
        );
    }

}
