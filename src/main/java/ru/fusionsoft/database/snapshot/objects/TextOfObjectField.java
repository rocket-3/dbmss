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
package ru.fusionsoft.database.snapshot.objects;

import org.cactoos.Func;
import org.cactoos.Text;
import ru.fusionsoft.lib.yaml.YamlRepresentative;

/**
 * The ways to extract field value of {@link YamlRepresentative}
 *  or {@link ru.fusionsoft.database.snapshot.DbObject}.
 * @since 0.1
 */
public class TextOfObjectField implements Text {

    /**
     * The ObjectFieldString encapsulated.
     */
    private final ObjectFieldString scalar;

    /**
     * Instantiates a new Text of object field.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     * @param absence The {@link Text} to be encapsulated.
     */
    public TextOfObjectField(
        final YamlRepresentative<?> object,
        final Text field,
        final Text absence
    ) {
        this(
            new ObjectFieldString(
                object,
                field,
                absence
            )
        );
    }

    /**
     * Instantiates a new Text of object field.
     * @param scalar The {@link ObjectFieldString} to be encapsulated.
     */
    public TextOfObjectField(final ObjectFieldString scalar) {
        this.scalar = scalar;
    }

    /**
     * Instantiates a new Text of object field.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     */
    public TextOfObjectField(
        final YamlRepresentative<?> object,
        final Text field
    ) {
        this(
            new ObjectFieldString(
                object,
                field
            )
        );
    }

    /**
     * Instantiates a new Text of object field.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     * @param presence Transformation if field present.
     * @param absence The {@link Text} to be encapsulated.
     * @checkstyle ParameterNumberCheck (200 lines).
     */
    public TextOfObjectField(
        final YamlRepresentative<?> object,
        final Text field,
        final Func<String, Text> presence,
        final Text absence
    ) {
        this(
            new ObjectFieldString(
                object,
                field,
                string -> presence.apply(string).asString(),
                absence
            )
        );
    }

    /**
     * Instantiates a new Text of object field.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     * @param presence Transformation if field present.
     */
    public TextOfObjectField(
        final YamlRepresentative<?> object,
        final Text field,
        final Func<String, Text> presence
    ) {
        this(
            new ObjectFieldString(
                object,
                field,
                string -> presence.apply(string).asString()
            )
        );
    }

    @Override
    public final String asString() {
        return this.scalar.value();
    }

}
