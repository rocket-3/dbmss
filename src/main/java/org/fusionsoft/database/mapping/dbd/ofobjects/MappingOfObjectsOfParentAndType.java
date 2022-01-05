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
package org.fusionsoft.database.mapping.dbd.ofobjects;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.BiFunc;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import org.fusionsoft.database.snapshot.objects.signature.ObjectType;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The {@link YamlMappingOfEntries} which constructs of {@link Objects} and their names entries from
 *  some parent {@link DbObject} and {@link ObjectType} perspective.
 * @param <T> The {@link Objects} included mapping type parameter.
 * @since 0.1
 */
public class MappingOfObjectsOfParentAndType<T extends YamlMapping> extends YamlMappingOfEntries {

    /**
     * Instantiates a new Mapping of objects of parent and type.
     * @param all The {@link Objects} to be encapsulated.
     * @param parent The {@link DbObject} to be encapsulated.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param ctor The {@link BiFunc} of mapping ctor to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public MappingOfObjectsOfParentAndType(
        final Objects<?> all,
        final DbObject<?> parent,
        final ObjectType<T> type,
        final BiFunc<Objects<?>, DbObject<T>, ? extends T> ctor
    ) {
        super(
            new UnwrapEntriesOfObjects<T>(
                all,
                new ObjectsWithParentAndType<>(
                    parent,
                    type,
                    all
                ),
                ctor
            )
        );
    }

    /**
     * Instantiates a new Mapping of objects of parent and type.
     * @param all The {@link Objects} to be encapsulated.
     * @param parent The {@link DbObject} to be encapsulated.
     * @param type The {@link ObjectType} to be encapsulated.
     */
    public MappingOfObjectsOfParentAndType(
        final Objects<?> all,
        final DbObject<?> parent,
        final ObjectType<T> type
    ) {
        this(
            all,
            parent,
            type,
            (Objects<?> objects, DbObject<T> object) -> type.node(
                new MappingOfRepresentative(object)
            )
        );
    }

}
