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
package org.fusionsoft.database.mapping.dbd.ofobjects;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.BiFunc;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import org.fusionsoft.database.snapshot.objects.signature.ObjectType;

public class EntriesOfObjectsOfParentAndType<M extends YamlMapping>
    extends UnwrapEntriesOfObjects<M> {

    /**
     * Ctor.
     * @param ctor The Func to construct {@link M} of origin {@link Objects}
     *  and {@link Objects} to take in scope.
     */
    public EntriesOfObjectsOfParentAndType(
        final Objects<?> objects,
        final DbObject<?> parent,
        final ObjectType<M> type,
        final BiFunc<Objects<?>, DbObject<?>, M> ctor
        ) {
        super(
            objects,
            new ObjectsWithParentAndType(parent, type, objects),
            ctor
        );
    }

    public EntriesOfObjectsOfParentAndType(
        final Objects<?> objects,
        final DbObject<?> parent,
        final ObjectType<M> type
    ) {
        super(
            objects,
            new ObjectsWithParentAndType(parent, type, objects),
            (Objects<?> all, DbObject<?> object) -> type.mapping(
                new MappingOfRepresentative(object)
            )
        );
    }

}
