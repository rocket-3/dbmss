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

/**
 * The {@link BiFunc} creating mapping with {@link T} type mappings, extracted from
 *  {@link Objects}, filtered by some {@link ObjectType} and {@link DbObject} parent.
 * @param <T>  The type of YamlMapping subclass instance created parameter.
 * @since 0.1
 */
public class DbdObjectsOfTypeMapping<T extends YamlMapping>
    implements BiFunc<Objects, DbObject<?>, YamlMapping> {

    /**
     * The ObjectType encapsulated.
     */
    private final ObjectType type;

    /**
     * The Unwrapping function encapsulated.
     */
    private final BiFunc<Objects, DbObject<?>, T> unwrapping;

    /**
     * Instantiates a new Dbd mapping of objects bi func.
     * @param type The ObjectType to be encapsulated.
     * @param unwraping The BiFunc of Objects, DbObject -> T to be encapsulated.
     */
    private DbdObjectsOfTypeMapping(
        final ObjectType type,
        final BiFunc<Objects, DbObject<?>, T> unwraping
    ) {
        this.type = type;
        this.unwrapping = unwraping;
    }

    /**
     * Instantiates a new Dbd mapping of objects bi func.
     * @param type The ObjectType to be encapsulated.
     * @param ctor The Func of YamlMapping -> T to be encapsulated.
     */
    public DbdObjectsOfTypeMapping(final ObjectType type, final Func<YamlMapping, T> ctor) {
        this(
            type,
            (objs, parent) -> ctor.apply(
                new MappingOfRepresentative(parent)
            )
        );
    }

    @Override
    public final YamlMapping apply(final Objects all, final DbObject<?> parent) {
        return new YamlMappingOfEntries(
            new UnwrapEntriesOfObjects<T>(
                all,
                new ObjectsWithParentAndType(
                    parent,
                    this.type,
                    all
                ),
                this.unwrapping
            )
        );
    }

}
