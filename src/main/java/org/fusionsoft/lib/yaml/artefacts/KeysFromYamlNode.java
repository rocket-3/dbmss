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
package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.ScalarOf;

public class KeysFromYamlNode extends IterableEnvelope<String> {

    public KeysFromYamlNode(final YamlNode yn) {
        this(yn, "");
    }

    public KeysFromYamlNode(final YamlNode yn, final String path) {
        super(
            new IterableOf<>(
                new ScalarOf<>(
                    () -> {
                        if (yn.type().equals(Node.MAPPING)) {
                            return new Joined<String>(
                                new Mapped<>(
                                    x -> path + x.asScalar().value(),
                                    yn.asMapping().keys()
                                ),
                                new Joined<>(
                                    new Mapped<>(
                                        x -> new KeysFromYamlNode(
                                            yn.asMapping().value(x),
                                            path + x.asScalar().value() + "/"
                                        ),
                                        yn.asMapping().keys()
                                    )
                                )
                            ).iterator();
                        } else {
                            return new IterableOf<String>().iterator();
                        }
                    }
                )
            )
        );
    }

}
