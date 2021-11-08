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
import org.cactoos.Func;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.entries.UnwrapEntriesOfObjects;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.filtered.ObjectsWithParentAndType;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

public class DbdMappingOfObjectsBiFunc<T extends YamlMapping>
    implements BiFunc<Objects, DbObject<?>, YamlMapping> {

    private final ObjectType type;

    BiFunc<Objects, DbObject<?>, T> unwrapping;

    public DbdMappingOfObjectsBiFunc(
        final ObjectType type,
        final BiFunc<Objects, DbObject<?>, T> unwraping
    ) {
        this.type = type;
        this.unwrapping = unwraping;
    }

    public DbdMappingOfObjectsBiFunc(final ObjectType type, final Func<YamlMapping, T> ctor) {
        this(
            type,
            (objs, obj) -> ctor.apply(
                new MappingOfRepresentative(obj)
            )
        );
    }

    @Override
    public YamlMapping apply(final Objects all, final DbObject<?> parent) {
        return new YamlMappingOfEntries(
            new UnwrapEntriesOfObjects<T>(
                all,
                new ObjectsWithParentAndType(
                    parent,
                    this.type,
                    all
                ),
                (objs, object) -> unwrapping.apply(
                    objs, object
                )
            )
        );
    }

}
