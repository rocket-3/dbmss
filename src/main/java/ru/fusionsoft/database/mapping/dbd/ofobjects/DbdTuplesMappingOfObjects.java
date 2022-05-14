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
package ru.fusionsoft.database.mapping.dbd.ofobjects;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.mapping.dbd.DbdTupleMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTuple;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The {@link YamlMappingOfScalar} of DBD/schemas/#schema/tuples document node,
 *  can be created of all {@link Iterable} of {@link DbObject}s
 *  and current parent schema {@link DbObject}.
 * @since 0.1
 */
public class DbdTuplesMappingOfObjects extends MappingOfObjectsOfParentAndType<DbdTupleMapping> {

    /**
     * Instantiates a new Yaml mapping of scalar.
     * @param objects The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param schema The schema {@link DbObject} to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> DbdTuplesMappingOfObjects(
        final Iterable<DbObject<Y>> objects,
        final DbObject<?> schema
    ) {
        super(
            objects,
            schema,
            new ObjectTypeTuple()
        );
    }

}
