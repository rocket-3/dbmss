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
package org.fusionsoft.database.yaml;

import com.amihaiemil.eoyaml.YamlInput;
import org.fusionsoft.lib.yaml.YamlInputEnvelope;

/**
 * The type of YamlInput alias that can be constructed of YamlInput.
 * @since 0.1
 * @checkstyle AbbreviationAsWordInNameCheck (100 lines)
 */
public class DBDYamlInput extends YamlInputEnvelope {

    /**
     * Instantiates a new Dbd yaml input.
     * @param input The YamlInput to be encapsulated.
     */
    public DBDYamlInput(final YamlInput input) {
        super(input);
    }

}
