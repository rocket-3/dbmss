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

import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.dbd.DbdDataMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeData;
import ru.fusionsoft.lib.collection.SingleOrEmptyFallback;
import ru.fusionsoft.lib.yaml.MappingEmpty;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The {@link DbdDataMapping}, extracted from {@link Objects}
 *  and {@link DbObject} of {@link DbdTableMapping} as parent.
 *  Also returns empty mapping, if there's no.
 * @since 0.1
 */
public class DbdDataMappingOfObjectsOrEmpty extends DbdDataMapping {

    /**
     * Instantiates a new Dbd data mapping of objects.
     * @param objects The {@link Objects} to be encapsulated.
     * @param parent The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public DbdDataMappingOfObjectsOrEmpty(
        final Objects<?> objects,
        final DbObject<DbdTableMapping> parent
    ) {
        super(
            new YamlMappingOfScalar(
                new SingleOrEmptyFallback<>(
                    new Mapped<>(
                        MapEntry::getValue,
                        new EntriesOfObjectsOfParentAndType<>(
                            objects,
                            parent,
                            new ObjectTypeData()
                        )
                    ),
                    new MappingEmpty()
                )
            )
        );
    }

}