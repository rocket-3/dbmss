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
import org.cactoos.Scalar;
import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.exception.ValueNotFoundException;
import ru.fusionsoft.lib.yaml.YamlRepresentative;

/**
 * The String of given field's name value.
 * @since 0.1
 */
public class ObjectFieldString extends ObjectFieldMapped<String> {

    /**
     * Instantiates a new Object field string.
     * @param object The {@link YamlRepresentative} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     */
    public ObjectFieldString(final YamlRepresentative<?> object, final Text field) {
        this(
            object,
            field,
            () -> {
                throw new ValueNotFoundException(
                    field.asString(),
                    object.asYaml().toString()
                );
            }
        );
    }

    /**
     * Instantiates a new Object field mapped.
     * @param object The {@link DbObject} to be encapsulated.
     * @param field The {@link Text} field of object.
     * @param absence The {@link Scalar} of what to return, if no value.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectFieldString(
        final YamlRepresentative<?> object,
        final Text field,
        final Text absence
    ) {
        super(
            object,
            field,
            node -> node.asScalar().value(),
            absence::asString
        );
    }

    /**
     * Instantiates a new Object field mapped.
     * @param object The {@link DbObject} to be encapsulated.
     * @param field The {@link Text} field of object.
     * @param presence The {@link Text} field of object.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectFieldString(
        final YamlRepresentative<?> object,
        final Text field,
        final Func<String, String> presence
    ) {
        this(
            object,
            field,
            presence,
            () -> {
                throw new ValueNotFoundException(
                    field.asString(),
                    object.asYaml().toString()
                );
            }
        );
    }

    /**
     * Instantiates a new Object field mapped.
     * @param object The {@link DbObject} to be encapsulated.
     * @param field The {@link Text} field of object.
     * @param presence The {@link Text} field of object.
     * @param absence The {@link Scalar} of what to return, if no value.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectFieldString(
        final YamlRepresentative<?> object,
        final Text field,
        final Func<String, String> presence,
        final Text absence
    ) {
        super(
            object,
            field,
            node -> presence.apply(node.asScalar().value()),
            absence::asString
        );
    }

}
