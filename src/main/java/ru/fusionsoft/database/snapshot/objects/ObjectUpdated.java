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

public class ObjectUpdated<T extends YamlNode> implements DbObject<T> {

    private final Unchecked<DbObject<? extends T>> scalar;

    private ObjectUpdated(final Scalar<DbObject<? extends T>> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar));
    }

    public ObjectUpdated(
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
    public ObjectSignature signature() {
        return this.scalar.value().signature();
    }

    @Override
    public T asYaml() {
        return this.scalar.value().asYaml();
    }

}
