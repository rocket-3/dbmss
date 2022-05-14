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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.BiFunc;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The {@link YamlMappingOfEntries} which constructs of {@link Iterable} of {@link DbObject}s
 *  and their names entries from
 *  some parent {@link DbObject} and {@link ObjectType} perspective.
 * @param <T> The {@link Iterable} of {@link DbObject}s included mapping type parameter.
 * @since 0.1
 */
public class MappingOfObjectsOfParentAndType<T extends YamlMapping> extends YamlMappingOfEntries {

    /**
     * Instantiates a new Mapping of objects of parent and type.
     * @param all The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param parent The {@link DbObject} to be encapsulated.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param ctor The {@link BiFunc} of mapping ctor to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public <Y extends YamlNode> MappingOfObjectsOfParentAndType(
        final Iterable<DbObject<Y>> all,
        final DbObject<?> parent,
        final ObjectType<T> type,
        final BiFunc<Iterable<DbObject<Y>>, DbObject<T>, ? extends T> ctor
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
     * @param all The {@link Iterable} of {@link DbObject}s to be encapsulated.
     * @param parent The {@link DbObject} to be encapsulated.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> MappingOfObjectsOfParentAndType(
        final Iterable<DbObject<Y>> all,
        final DbObject<?> parent,
        final ObjectType<T> type
    ) {
        this(
            all,
            parent,
            type,
            (Iterable<DbObject<Y>> objects, DbObject<T> object) -> type.node(
                new MappingOfRepresentative(object)
            )
        );
    }

}
