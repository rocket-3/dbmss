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
package ru.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.lib.yaml.artefacts.TextOfScalarNode;

/**
 * The MapEntries of Text and YamlNode constructed of {@link YamlMapping}.
 * @since 0.1
 */
public class EntriesOfYamlMapping
    extends IterableEnvelope<Map.Entry<? extends Text, ? extends YamlNode>> {

    /**
     * Ctor.
     * @param mapping The wrapped mapping.
     */
    public EntriesOfYamlMapping(final YamlMapping mapping) {
        super(
            new Mapped<>(
                entry -> new MapEntry<>(
                    new TextOfScalarNode(entry.getKey()),
                    entry.getValue()
                ),
                new NodeEntriesOfYamlMapping(mapping)
            )
        );
    }

}
