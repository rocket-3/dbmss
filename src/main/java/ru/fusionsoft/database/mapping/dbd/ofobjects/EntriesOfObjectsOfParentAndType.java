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
import org.cactoos.BiFunc;
import ru.fusionsoft.database.mapping.MappingOfRepresentative;
import ru.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * The {@link UnwrapEntriesOfObjects}, specified by {@link ObjectType}, and parent {@link Objects}.
 * @param <T> The type of mapping parameter.
 * @since 0.1
 */
public class EntriesOfObjectsOfParentAndType<T extends YamlMapping>
    extends UnwrapEntriesOfObjects<T> {

    /**
     * Ctor.
     * @param objects The {@link Objects} to be encapsulated.
     * @param parent The {@link DbObject} to be encapsulated.
     * @param type The {@link T} {@link ObjectType} to be encapsulated.
     * @param ctor The Func to construct {@link T}
     *  of {@link Objects} of {@link T }and all {@link Objects}.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public EntriesOfObjectsOfParentAndType(
        final Objects<?> objects,
        final DbObject<?> parent,
        final ObjectType<T> type,
        final BiFunc<Objects<?>, DbObject<T>, T> ctor
    ) {
        super(
            objects,
            new ObjectsWithParentAndType<>(parent, type, objects),
            ctor
        );
    }

    /**
     * Instantiates a new Entries of objects of parent and type.
     * @param objects The {@link Objects} to be encapsulated.
     * @param parent The {@link DbObject} to be encapsulated.
     * @param type The {@link T} {@link ObjectType} to be encapsulated.
     */
    public EntriesOfObjectsOfParentAndType(
        final Objects<?> objects,
        final DbObject<?> parent,
        final ObjectType<T> type
    ) {
        super(
            objects,
            new ObjectsWithParentAndType<>(parent, type, objects),
            (Objects<?> all, DbObject<T> object) -> type.node(
                new MappingOfRepresentative(object)
            )
        );
    }

}