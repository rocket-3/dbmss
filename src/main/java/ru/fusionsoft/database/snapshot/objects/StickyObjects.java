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
import org.cactoos.iterable.Sticky;
import ru.fusionsoft.database.snapshot.Objects;

/**
 * The type of Objects that are 'sticky' -> queried only one time.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class StickyObjects<T extends YamlNode> extends ObjectsEnvelope<T> {

    /**
     * Ctor.
     * @param objects The wrapped {@link Objects}
     */
    public StickyObjects(final Objects<T> objects) {
        super(new Sticky<>(objects));
    }

}