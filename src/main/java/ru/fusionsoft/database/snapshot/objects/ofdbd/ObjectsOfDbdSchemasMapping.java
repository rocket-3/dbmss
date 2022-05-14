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

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * The Objects that can be constructed of DBD/schemas mapping.
 * @since 0.1
 */
public class ObjectsOfDbdSchemasMapping extends ObjectsOfScalar<YamlNode> {

    /**
     * Ctor.
     * @param node The wrapped node
     */
    public ObjectsOfDbdSchemasMapping(final YamlNode node) {
        super(
            new Ternary<Iterable<? extends DbObject<YamlNode>>>(
                () -> node.type().equals(Node.MAPPING),
                () -> new ObjectsJoined(
                    new IterableOfClassFromYamlNode<>(
                        ObjectsOfDbdSchemaMapping::new,
                        node.asMapping()
                    )
                ),
                () -> new IterableOf<>()
            )
        );
    }

}
