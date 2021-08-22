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

import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.YamlInput;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlStream;
import java.io.IOException;

public class YamlInputEnvelope implements YamlInput {

    private final YamlInput yamlInput;

    public YamlInputEnvelope(final YamlInput yamlInput) {
        this.yamlInput = yamlInput;
    }

    @Override
    public YamlMapping readYamlMapping() throws IOException {
        return this.yamlInput.readYamlMapping();
    }

    @Override
    public YamlSequence readYamlSequence() throws IOException {
        return this.yamlInput.readYamlSequence();
    }

    @Override
    public YamlStream readYamlStream() throws IOException {
        return this.yamlInput.readYamlStream();
    }

    @Override
    public Scalar readPlainScalar() throws IOException {
        return this.yamlInput.readPlainScalar();
    }

    @Override
    public Scalar readFoldedBlockScalar() throws IOException {
        return this.yamlInput.readFoldedBlockScalar();
    }

    @Override
    public Scalar readLiteralBlockScalar() throws IOException {
        return this.yamlInput.readLiteralBlockScalar();
    }

}
