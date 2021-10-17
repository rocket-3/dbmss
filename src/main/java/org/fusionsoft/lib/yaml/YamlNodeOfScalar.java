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

import com.amihaiemil.eoyaml.Comment;
import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import com.amihaiemil.eoyaml.YamlStream;
import com.amihaiemil.eoyaml.exceptions.YamlReadingException;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * Class delegating everything to an another YamlNode.
 * @since 0.1
 * @checkstyle ParameterNameCheck (100 lines)
 */
@SuppressWarnings("PMD")
public class YamlNodeOfScalar implements YamlNode {

    /**
     * The YamlNode encapsulated.
     */
    private final Unchecked<YamlNode> scalar;

    /**
     * Instantiates a new Yaml node envelope.
     * @param scalar The Scalar of YamlNode to be encapsulated.
     */
    public YamlNodeOfScalar(final org.cactoos.Scalar<YamlNode> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar));
    }

    @Override
    public boolean isEmpty() {
        return this.scalar.value().isEmpty();
    }

    @Override
    public final Comment comment() {
        return this.scalar.value().comment();
    }

    @Override
    public final Node type() {
        return this.scalar.value().type();
    }

    @Override
    public final Scalar asScalar() throws YamlReadingException, ClassCastException {
        return this.scalar.value().asScalar();
    }

    @Override
    public final YamlMapping asMapping() throws YamlReadingException, ClassCastException {
        return this.scalar.value().asMapping();
    }

    @Override
    public final YamlSequence asSequence() throws YamlReadingException, ClassCastException {
        return this.scalar.value().asSequence();
    }

    @Override
    public final YamlStream asStream() throws YamlReadingException, ClassCastException {
        return this.scalar.value().asStream();
    }

    @Override
    public final <T extends YamlNode> T asClass(
        final Class<T> clazz,
        final Node type
    ) throws YamlReadingException, ClassCastException {
        return this.scalar.value().asClass(clazz, type);
    }

    @Override
    public final int compareTo(final YamlNode o) {
        return this.scalar.value().compareTo(o);
    }

    @Override
    public final String toString() {
        return this.scalar.value().toString();
    }

}
