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
import org.cactoos.func.UncheckedFunc;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;

/**
 * The type of that can be constructed of.
 * @param <T> The type of {@link YamlNode} parameter.
 * @since 0.1
 */
public class DbObjectCasted<T extends YamlNode> implements DbObject<T> {

    /**
     * The {@link DbObject} encapsulated.
     */
    private final DbObject<?> object;

    /**
     * The {@link UncheckedFunc} of {@link YamlNode}, {@link T} encapsulated.
     */
    private final UncheckedFunc<? super YamlNode, T> cast;

    /**
     * Instantiates a new Object casted.
     * @param cast The cast {@link Func} of {@link YamlNode}, {@link T} to be encapsulated.
     * @param object The {@link DbObject} to be encapsulated.
     */
    public DbObjectCasted(final Func<? super YamlNode, T> cast, final DbObject<?> object) {
        this.object = object;
        this.cast = new UncheckedFunc<>(cast);
    }

    @Override
    public final ObjectSignature signature() {
        return this.object.signature();
    }

    @Override
    public final T asYaml() {
        return this.cast.apply(this.object.asYaml());
    }

    @Override
    public final String toString() {
        return this.object.toString();
    }

    @Override
    public final boolean equals(final Object obj) {
        return this.object.equals(obj);
    }

    @Override
    public final int hashCode() {
        return this.object.hashCode();
    }

}
