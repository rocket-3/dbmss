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

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;
import java.io.InputStream;
import java.nio.file.Path;
import org.cactoos.Input;
import org.cactoos.Text;
import org.cactoos.io.InputStreamOf;
import org.cactoos.scalar.Sticky;

/**
 * The type of on-demand YamlMapping that can be constructed of YamlInput.
 * @since 0.1
 * @checkstyle LineLengthCheck (200 lines)
 */
public class YamlMappingOf extends YamlMappingEnvelope {

    /**
     * Instantiates a new YamlMapping of YamlNode, that is used on demand.
     * @param input The YamlNode to be used.
     */
    public YamlMappingOf(final YamlNode input) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    input::asMapping
                )
            )
        );
    }

    /**
     * Instantiates a new YamlMapping of YamlInput, that is used on demand.
     * @param input The YamlInput to be used.
     */
    public YamlMappingOf(final InputStream input) {
        super(
            new YamlMappingOfScalar(
                new Sticky<>(
                    () -> {
                        return Yaml.createYamlInput(input).readYamlMapping();
                    }
                )
            )
        );
    }

    /**
     * Instantiates a new Yaml mapping of.
     * @param input The {@link Input} to be encapsulated.
     */
    public YamlMappingOf(final Input input) {
        this(
            new InputStreamOf(input)
        );
    }

    /**
     * Instantiates a new Yaml mapping of.
     * @param input The {@link Text} to be encapsulated.
     */
    public YamlMappingOf(final Text input) {
        this(
            new InputStreamOf(input)
        );
    }

    /**
     * Instantiates a new Yaml mapping of any String or CharSequence.
     * @param input The input
     */
    public YamlMappingOf(final CharSequence input) {
        this(
            new InputStreamOf(input)
        );
    }

    /**
     * Instantiates a new YamlMapping of YamlInput, that is used on demand.
     * @param path The {@link Path} to be used.
     */
    public YamlMappingOf(final Path path) {
        this(
            new InputStreamOf(path)
        );
    }

}
