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
import com.amihaiemil.eoyaml.YamlNodeNotFoundException;
import org.cactoos.Fallback;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.ScalarWithFallback;

/**
 * The decorator of {@link YamlMapping} that uses {@link MappingEmpty} as delegate instead of
 *  original, when unchecked {@link YamlNodeNotFoundException} occurs.
 * @since 0.1
 */
public class YamlMappingOrEmptyWhenNoValueNotFound extends YamlMappingOfScalar {

    /**
     * Instantiates a new Yaml mapping or empty when no value not found.
     * @param mapping The {@link YamlMapping} to be encapsulated.
     */
    public YamlMappingOrEmptyWhenNoValueNotFound(final YamlMapping mapping) {
        super(
            new ScalarWithFallback<>(
                new ScalarOf<>(
                    () -> {
                        mapping.keys();
                        return mapping;
                    }
                ),
                new Fallback.From<>(
                    YamlNodeNotFoundException.class,
                    e -> new MappingEmpty()
                )
            )
        );
    }

}
