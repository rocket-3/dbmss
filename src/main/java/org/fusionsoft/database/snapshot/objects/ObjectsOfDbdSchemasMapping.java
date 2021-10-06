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
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Joined;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.lib.yaml.artefacts.IterableOfClassFromYamlNode;

/**
 * The Objects that can be constructed of DBD/schemas mapping.
 * @since 0.1
 */
public class ObjectsOfDbdSchemasMapping extends IterableEnvelope<DbObject> implements Objects {

    /**
     * Ctor.
     * @param mapping The wrapped mapping
     */
    public ObjectsOfDbdSchemasMapping(final YamlMapping mapping) {
        super(
            new Joined<DbObject>(
                new IterableOfClassFromYamlNode<>(
                    ObjectsOfDbdSchemaMapping::new,
                    mapping
                )
            )
        );
    }

}
