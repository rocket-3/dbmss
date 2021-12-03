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
package org.fusionsoft.lib.yaml.artefacts;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Replaced;
import org.cactoos.text.Sub;
import org.fusionsoft.lib.text.IterableOfRegexpMatches;

public class TextIterableOfFlowYamlSequence extends IterableEnvelope<Text> {

    public TextIterableOfFlowYamlSequence(final Text text, final RegexpOfFlowYamlSequenceValues regexp) {
        super(
            new Mapped<Text>(
                x -> new Replaced(x, "\"\"", "\""),
                new IterableOfRegexpMatches(
                    regexp::pattern,
                    regexp::extract,
                    new Sub(
                        text,
                        1,
                        () -> text.asString().length() - 1
                    )
                )
            )
        );
    }

    public TextIterableOfFlowYamlSequence(final Text text) {
        this(text, new RegexpOfFlowYamlSequenceValues());
    }

    public TextIterableOfFlowYamlSequence(final YamlNode node) {
        this(new TextOfScalarNode(node));
    }

}
