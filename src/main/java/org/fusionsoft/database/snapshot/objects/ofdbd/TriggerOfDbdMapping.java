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
package org.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.fusionsoft.database.mapping.dbd.DbdTriggerMapping;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The {@link SimpleDbObject}, can be constructed of {@link DbdTriggerMapping}.
 * @since 0.1
 */
public class TriggerOfDbdMapping extends SimpleDbObject<DbdTriggerMapping> {

    /**
     * Instantiates a new Trigger of dbd mapping.
     * @param root The {@link YamlMapping} to be encapsulated.
     * @param key The {@link YamlNode} to be encapsulated.
     * @param name The {@link ObjectName} to be encapsulated.
     */
    public TriggerOfDbdMapping(final YamlMapping root, final YamlNode key, final ObjectName name) {
        this(
            new DbdTriggerMapping(new YamlMappingOfPath(root, key)),
            new SimpleObjectName(
                name,
                new TextOfScalarNode(key)
            )
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param yaml The {@link DbdTriggerMapping} to be encapsulated.
     * @param name The {@link ObjectName} to be encapsulated.
     */
    public TriggerOfDbdMapping(final DbdTriggerMapping yaml, final ObjectName name) {
        super(
            yaml,
            new SimpleObjectSignature(
                name,
                new ObjectTypeTrigger()
            )
        );
    }

}
