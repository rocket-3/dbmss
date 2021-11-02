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
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.Filtered;
import org.cactoos.scalar.Not;
import org.cactoos.scalar.Or;
import org.fusionsoft.lib.text.JsonUndefinedText;

/**
 * The type of {@link YamlMapping} decorator that filters out 'null' scalar nodes.
 * @since 0.1
 */
public class MappingWithoutNullScalars extends YamlMappingOfEntries {

    /**
     * Instantiates a new Mapping without null scalars.
     * @param mapping The YamlMapping to be encapsulated.
     */
    public MappingWithoutNullScalars(final YamlMapping mapping) {
        super(
            new Filtered<Map.Entry<? extends Text, ? extends YamlNode>>(
                entry -> new Or(
                    new Not(
                        () -> entry.getValue().type().equals(
                            Node.SCALAR
                        )
                    ),
                    new Not(
                        () -> entry.getValue().asScalar().value().equals(
                            new JsonUndefinedText().asString()
                        )
                    )
                ).value(),
                new EntriesOfYamlMapping(mapping)
            )
        );
    }

}
