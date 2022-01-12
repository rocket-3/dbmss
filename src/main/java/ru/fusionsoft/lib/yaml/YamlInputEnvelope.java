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

import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.YamlInput;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlStream;
import java.io.IOException;

/**
 * The type {@link YamlInput} of that can be constructed of another.
 * Can be used for composition.
 * @since 0.1
 */
public class YamlInputEnvelope implements YamlInput {

    /**
     * The YamlInput encapsulated.
     */
    private final YamlInput input;

    /**
     * Instantiates a new Yaml input envelope.
     * @param input The YamlInput to be encapsulated.
     */
    public YamlInputEnvelope(final YamlInput input) {
        this.input = input;
    }

    @Override
    public final YamlMapping readYamlMapping() throws IOException {
        return this.input.readYamlMapping();
    }

    @Override
    public final YamlSequence readYamlSequence() throws IOException {
        return this.input.readYamlSequence();
    }

    @Override
    public final YamlStream readYamlStream() throws IOException {
        return this.input.readYamlStream();
    }

    @Override
    public final Scalar readPlainScalar() throws IOException {
        return this.input.readPlainScalar();
    }

    @Override
    public final Scalar readFoldedBlockScalar() throws IOException {
        return this.input.readFoldedBlockScalar();
    }

    @Override
    public final Scalar readLiteralBlockScalar() throws IOException {
        return this.input.readLiteralBlockScalar();
    }

}
