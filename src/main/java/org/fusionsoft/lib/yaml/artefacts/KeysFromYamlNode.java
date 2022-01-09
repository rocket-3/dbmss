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
package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.func.FuncOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetEnvelope;
import org.cactoos.set.SetOf;

/**
 * The type of String Iterable that extracts all keys from YamlNode.
 * @since 0.1
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
public class KeysFromYamlNode extends SetEnvelope<String> {

    /**
     * Instantiates a set of keys from yaml node.
     * @param mapping The YamlMapping to be used.
     */
    public KeysFromYamlNode(final YamlNode mapping) {
        super(
            new SetOf<>(
                new Mapped<>(
                    new FuncOf<>((YamlNode node) -> node.asScalar().value()),
                    mapping.asMapping().keys()
                )
            )
        );
    }

}
