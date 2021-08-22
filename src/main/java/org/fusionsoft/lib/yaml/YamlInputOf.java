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
 *
 */
package org.fusionsoft.lib.yaml;

import com.amihaiemil.eoyaml.Yaml;
import java.io.InputStream;
import java.nio.file.Path;
import org.cactoos.Text;
import org.cactoos.io.InputStreamOf;

public class YamlInputOf extends YamlInputEnvelope {

    public YamlInputOf(final InputStream inputStream) {
        super(Yaml.createYamlInput(inputStream));
    }

    public YamlInputOf(final Path pathToFile) {
        this(
            new InputStreamOf(pathToFile)
        );
    }

    public YamlInputOf(final CharSequence chars) {
        this(
            new InputStreamOf(chars)
        );
    }

    public YamlInputOf(final Text text) {
        this(
            new InputStreamOf(text)
        );
    }

}
