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

import com.amihaiemil.eoyaml.Yaml;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * The type of YamlNode of some single-line text.
 * @since 0.1
 */
public class YamlPlainScalarOf extends YamlNodeOfScalar {

    /**
     * Instantiates a new plain scalar YamlNode of some single-line text.
     * @param text The text to be encapsulated.
     */
    public YamlPlainScalarOf(final Text text) {
        super(
            () -> Yaml.createYamlScalarBuilder()
                .addLine(new UncheckedText(text).asString())
                .buildPlainScalar()
        );
    }

    /**
     * Instantiates a new plain scalar YamlNode of some single-line text.
     * @param text The string to be encapsulated.
     */
    public YamlPlainScalarOf(final String text) {
        this(new TextOf(text));
    }

}
