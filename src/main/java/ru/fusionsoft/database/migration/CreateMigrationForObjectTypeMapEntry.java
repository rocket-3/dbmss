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
package ru.fusionsoft.database.migration;

import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.BiFunc;
import org.cactoos.Scalar;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.DbObjectCasted;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;

/**
 * This creates {@link ObjectType} to {@link Migration} {@link Scalar} map entry.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class CreateMigrationForObjectTypeMapEntry<T extends YamlNode>
    implements BiFunc<DbObject<?>, Dbms, Map.Entry<ObjectType<T>, Scalar<Migration>>> {

    /**
     * The ObjectType encapsulated.
     */
    private final ObjectType<T> type;

    /**
     * The specific-type {@link Migration} ctor encapsulated.
     */
    private final BiFunc<DbObject<T>, Dbms, Migration> ctor;

    /**
     * Instantiates a new Type to migration wiring.
     * @param type The {@link ObjectType} to be encapsulated.
     * @param ctor The specific-type {@link Migration} ctor to be encapsulated.
     */
    public CreateMigrationForObjectTypeMapEntry(
        final ObjectType<T> type,
        final BiFunc<DbObject<T>, Dbms, Migration> ctor
    ) {
        this.type = type;
        this.ctor = ctor;
    }

    @Override
    public final Map.Entry<ObjectType<T>, Scalar<Migration>> apply(
        final DbObject<?> object,
        final Dbms dbms
    ) {
        return new MapEntry<>(
            this.type,
            () -> this.ctor.apply(
                new DbObjectCasted<T>(this.type, object),
                dbms
            )
        );
    }

}
