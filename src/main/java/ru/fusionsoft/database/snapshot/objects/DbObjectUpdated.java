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
package ru.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;

/**
 * The {@link DbObject} which YAML representation being updated by some transform func.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class DbObjectUpdated<T extends YamlNode> implements DbObject<T> {

    /**
     * The scalar of object.
     */
    private final Unchecked<DbObject<? extends T>> scalar;

    /**
     * Instantiates a new Db object updated.
     * @param scalar The {@link Scalar} of {@link DbObject} to be encapsulated.
     */
    private DbObjectUpdated(final Scalar<DbObject<? extends T>> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar));
    }

    /**
     * Instantiates a new Db object updated.
     * @param update The YAML transformation func.
     * @param object The original object to be encapsulated.
     */
    public DbObjectUpdated(
        final Func<YamlNode, ? extends T> update,
        final DbObject<?> object
    ) {
        this(
            () -> new SimpleDbObject<T>(
                update.apply(object.asYaml()),
                object.signature()
            )
        );
    }

    @Override
    public final ObjectSignature signature() {
        return this.scalar.value().signature();
    }

    @Override
    public final T asYaml() {
        return this.scalar.value().asYaml();
    }

}
