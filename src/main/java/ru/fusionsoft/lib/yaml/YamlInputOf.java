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
import java.io.InputStream;
import java.nio.file.Path;
import org.cactoos.Input;
import org.cactoos.Text;
import org.cactoos.io.InputStreamOf;
import org.cactoos.io.UncheckedInput;

/**
 * The type of YamlInput that can be constructed of different sources.
 * @since 0.1
 */
public class YamlInputOf extends YamlInputEnvelope {

    /**
     * Instantiates a new Yaml input of InputStream.
     * @param stream The InputStream to be encapsulated.
     */
    public YamlInputOf(final InputStream stream) {
        super(Yaml.createYamlInput(stream));
    }

    /**
     * Instantiates a new Yaml input of {@link Input}.
     * @param input The InputStream to be encapsulated.
     */
    public YamlInputOf(final Input input) {
        this(new UncheckedInput(input).stream());
    }

    /**
     * Instantiates a new Yaml input of Path to file.
     * @param path The Path to be encapsulated.
     */
    public YamlInputOf(final Path path) {
        this(
            new InputStreamOf(path)
        );
    }

    /**
     * Instantiates a new Yaml input of any CharSequence.
     * @param chars The CharSequence to be encapsulated.
     */
    public YamlInputOf(final CharSequence chars) {
        this(
            new InputStreamOf(chars)
        );
    }

    /**
     * Instantiates a new Yaml input of.
     * @param text The Text to be encapsulated.
     */
    public YamlInputOf(final Text text) {
        this(
            new InputStreamOf(text)
        );
    }

}
