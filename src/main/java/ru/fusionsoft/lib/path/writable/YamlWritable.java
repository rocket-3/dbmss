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
package ru.fusionsoft.lib.path.writable;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;
import java.nio.file.Path;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.io.WriterTo;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.Writable;
import ru.fusionsoft.lib.yaml.SimpleYamlRepresentative;
import ru.fusionsoft.lib.yaml.YamlRepresentative;

/**
 * The {@link Writable} of {@link YamlRepresentative}/{@link YamlNode}.
 * @since 0.1
 */
public class YamlWritable implements Writable {

    /**
     * The YamlRepresentative encapsulated.
     */
    private final YamlRepresentative<?> yaml;

    /**
     * The Text encapsulated.
     */
    private final Text name;

    /**
     * Instantiates a new Writable yaml document.
     * @param yaml The YamlRepresentative to be encapsulated.
     * @param name The Text of file name to be encapsulated.
     */
    public YamlWritable(
        final YamlRepresentative<?> yaml,
        final Text name
    ) {
        this.yaml = yaml;
        this.name = name;
    }

    /**
     * Instantiates a new Writable yaml document.
     * @param scalar The Scalar of YamlNode to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    public YamlWritable(
        final Scalar<YamlNode> scalar,
        final Text name
    ) {
        this(new SimpleYamlRepresentative<>(scalar), name);
    }

    /**
     * Instantiates a new Writable yaml document.
     * @param yaml The YamlNode to be encapsulated.
     * @param name The Text to be encapsulated.
     */
    public YamlWritable(
        final YamlNode yaml,
        final Text name
    ) {
        this(new ScalarOf<>(() -> yaml), name);
    }

    @Override
    public final void writeTo(final Directory directory) {
        new Unchecked<>(
            () -> {
                final Path path = directory.value().resolve(
                    new UncheckedText(this.name).asString()
                );
                final YamlNode node = this.yaml.asYaml();
                Yaml.createYamlPrinter(new WriterTo(path)).print(node);
                return true;
            }
        ).value();
    }

}
