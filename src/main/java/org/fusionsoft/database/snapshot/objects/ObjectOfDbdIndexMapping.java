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
import org.fusionsoft.database.snapshot.NaiveDbObject;
import org.fusionsoft.database.snapshot.ObjectType;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.yaml.YamlMappingOfPath;
import org.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The db index object of DBD/schemas/#schema/tables/#table/indexes/#index node.
 * @since 0.1
 */
public class ObjectOfDbdIndexMapping extends NaiveDbObject {

    /**
     * Instantiates a new Object of dbd index mapping.
     * @param root The YamlMapping to be encapsulated.
     * @param key The YamlNode to be encapsulated.
     * @param table The Text to be encapsulated.
     */
    public ObjectOfDbdIndexMapping(
        final YamlMapping root,
        final YamlNode key,
        final Text table) {
        this(
            new YamlMappingOfPath(root, key),
            new FullObjectName(table, new TextOfScalarNode(key))
        );
    }

    /**
     * Instantiates a new Object of dbd index mapping.
     * @param mapping The YamlMapping to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    public ObjectOfDbdIndexMapping(final YamlMapping mapping, final Text name) {
        super(
            mapping,
            new SimpleObjectSignature(
                new FullObjectName(name),
                ObjectType.INDEX
            )
        );
    }

}
