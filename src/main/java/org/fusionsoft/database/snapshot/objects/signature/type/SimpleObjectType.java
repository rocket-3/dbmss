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
package org.fusionsoft.database.snapshot.objects.signature.type;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;
import org.fusionsoft.database.snapshot.objects.signature.ObjectType;

public class SimpleObjectType<M extends YamlMapping> implements ObjectType<M> {

    private final UncheckedFunc<? super YamlMapping, M> mapping;

    private final String text;

    public SimpleObjectType(final Func<? super YamlMapping, M> mapping, final String text) {
        this.mapping = new UncheckedFunc<>(mapping);
        this.text = text;
    }

    @Override
    public M mapping(final YamlMapping mapping) {
        return this.mapping.apply(mapping);
    }

    @Override
    public String asString() {
        return this.text;
    }

}
