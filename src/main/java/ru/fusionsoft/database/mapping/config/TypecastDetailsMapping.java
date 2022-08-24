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
package ru.fusionsoft.database.mapping.config;

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.lib.yaml.YamlMappingHasKeys;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The mapping of type to type cast details, part of {@link MigrationConfigMapping}.
 * @since 0.1
 */
public class TypecastDetailsMapping extends YamlMappingHasKeys {

    /**
     * Instantiates a new Typecast details mapping.
     * @param mapping The {@link YamlMapping} to be encapsulated.
     */
    private TypecastDetailsMapping(final YamlMapping mapping) {
        super(
            mapping,
            TypecastDetailsFields.IMPLICIT
        );
    }

    /**
     * Instantiates a new Typecast details mapping.
     * @param entries The entries of {@link TypecastDetailsFields}.
     */
    @SafeVarargs
    public TypecastDetailsMapping(
        final Map.Entry<TypecastDetailsFields, Scalar<Boolean>>... entries
    ) {
        this(new IterableOf<>(entries));
    }

    /**
     * Instantiates a new Typecast details mapping.
     * @param entries The entries of {@link TypecastDetailsFields}.
     */
    public TypecastDetailsMapping(
        final Iterable<Map.Entry<TypecastDetailsFields, Scalar<Boolean>>> entries
    ) {
        this(
            new YamlMappingOfEntries(
                new Mapped<Map.Entry<? extends Text, ? extends YamlNode>>(
                    entry -> new ScalarEntry(
                        entry.getKey().asString(),
                        entry.getValue().value().toString()
                    ),
                    entries
                )
            )
        );
    }

}
