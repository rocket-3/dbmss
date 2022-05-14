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

import com.amihaiemil.eoyaml.Node;
import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.yaml.YamlNodeOfPath;
import ru.fusionsoft.lib.yaml.artefacts.KeysFromYamlNode;

/**
 * This scalar constructs any {@link T} object of some {@link DbObject}s field.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class ObjectFieldMapped<T> implements Scalar<T> {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<?> object;

    /**
     * The Text encapsulated.
     */
    private final Text field;

    /**
     * The conversion func of existing field encapsulated.
     */
    private final Func<YamlNode, T> presence;

    /**
     * The Scalar to use, when no value present.
     */
    private final Scalar<T> absence;

    /**
     * Instantiates a new Object field mapped.
     * @param object The {@link DbObject} to be encapsulated.
     * @param field The {@link Text} to be encapsulated.
     * @param presence The {@link Func} of {@link YamlNode} and {@link T} to be encapsulated.
     * @param  absence The {@link Scalar} of {@link T} to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public ObjectFieldMapped(
        final DbObject<?> object,
        final Text field,
        final Func<YamlNode, T> presence,
        final Scalar<T> absence
    ) {
        this.object = object;
        this.field = field;
        this.presence = presence;
        this.absence = absence;
    }

    @Override
    public final T value() {
        return new Unchecked<>(
            new Ternary<T>(
                new And(
                    () -> this.object.asYaml().type().equals(
                        Node.MAPPING
                    ),
                    () -> new KeysFromYamlNode(
                        this.object.asYaml().asMapping()
                    ).contains(this.field.asString())
                ),
                () -> this.presence.apply(
                    new YamlNodeOfPath(
                        this.object.asYaml(),
                        this.field
                    )
                ),
                () -> this.absence.value()
            )
        ).value();
    }

}
