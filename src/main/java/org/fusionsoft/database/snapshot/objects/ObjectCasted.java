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
package org.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.ObjectSignature;

public class ObjectCasted<T extends YamlMapping> implements DbObject<T> {

    private final DbObject<? extends YamlMapping> object;

    private final UncheckedFunc<? super YamlMapping, T> cast;

    public ObjectCasted(final Func<? super YamlMapping, T> cast, final DbObject<?> object) {
        this.object = object;
        this.cast = new UncheckedFunc<>(cast);
    }

    @Override
    public ObjectSignature signature() {
        return this.object.signature();
    }

    @Override
    public T asYaml() {
        return cast.apply(object.asYaml());
    }

}
