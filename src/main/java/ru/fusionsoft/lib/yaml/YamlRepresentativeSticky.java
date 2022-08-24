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

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlMappingBuilder;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlScalarBuilder;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlSequenceBuilder;
import com.amihaiemil.eoyaml.YamlStream;
import com.amihaiemil.eoyaml.YamlStreamBuilder;
import org.cactoos.Scalar;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;

/**
 * The {@link YamlRepresentative} which pre-renders and stores all nodes,
 *  i.e. forced eager evaluation.
 * @since 0.1
 */
public class YamlRepresentativeSticky extends SimpleYamlRepresentative<YamlNode> {

    /**
     * Instantiates a new Yaml representative sticky.
     * @param original The node
     */
    public YamlRepresentativeSticky(final YamlNode original) {
        super(
            new org.cactoos.scalar.Sticky<>(
                () -> {
                    return new MapOf<>(
                        new MapEntry<Node, Scalar<YamlNode>>(
                            Node.MAPPING,
                            () -> {
                                final YamlMapping mapping = Yaml.createYamlInput(
                                    original.asMapping().toString()
                                ).readYamlMapping();
                                YamlMappingBuilder yaml = Yaml.createYamlMappingBuilder();
                                for (final YamlNode key : mapping.keys()) {
                                    yaml = yaml.add(
                                        key.asScalar().value(),
                                        new YamlRepresentativeSticky(mapping.value(key)).asYaml()
                                    );
                                }
                                return yaml.build(mapping.comment().value());
                            }
                        ),
                        new MapEntry<Node, Scalar<YamlNode>>(
                            Node.SEQUENCE,
                            () -> {
                                final YamlSequence sequence = original.asSequence();
                                YamlSequenceBuilder yaml = Yaml.createYamlSequenceBuilder();
                                for (final YamlNode value : sequence.values()) {
                                    yaml = yaml.add(
                                        new YamlRepresentativeSticky(value).asYaml()
                                    );
                                }
                                return yaml.build(sequence.comment().value());
                            }
                        ),
                        new MapEntry<Node, Scalar<YamlNode>>(
                            Node.STREAM,
                            () -> {
                                final YamlStream stream = original.asStream();
                                YamlStreamBuilder yaml = Yaml.createYamlStreamBuilder();
                                for (final YamlNode node : stream.values()) {
                                    yaml = yaml.add(
                                        new YamlRepresentativeSticky(node).asYaml()
                                    );
                                }
                                return yaml.build();
                            }
                        ),
                        new MapEntry<Node, Scalar<YamlNode>>(
                            Node.SCALAR,
                            () -> {
                                final com.amihaiemil.eoyaml.Scalar scalar = original.asScalar();
                                final YamlScalarBuilder builder = Yaml.createYamlScalarBuilder()
                                    .addLine(scalar.value());
                                return new Ternary<>(
                                    () -> scalar.value().contains("\n"),
                                    () -> builder.buildLiteralBlockScalar(scalar.comment().value()),
                                    () -> builder.buildPlainScalar(scalar.comment().value())
                                ).value();
                            }
                        )
                    ).get(original.type()).value();
                }
            )
        );
    }

}
