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
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;

/**
 * The entries of {@link YamlMapping} iterable.
 * @since 0.1
 */
public class NodeEntriesOfYamlMapping extends IterableEnvelope<MapEntry<YamlNode, YamlNode>> {

    /**
     * Ctor.
     * @param mapping The wrapped mapping
     */
    public NodeEntriesOfYamlMapping(final YamlMapping mapping) {
        super(
            new Mapped<>(
                key -> new MapEntry<>(key, mapping.value(key)),
                new IterableOf<>(() -> mapping.keys().iterator())
            )
        );
    }

}
