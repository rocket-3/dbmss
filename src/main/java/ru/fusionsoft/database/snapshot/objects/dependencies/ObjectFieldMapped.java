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
package ru.fusionsoft.database.snapshot.objects.dependencies;

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

public class ObjectFieldMapped<T> implements Scalar<T> {

    private final DbObject<?> object;

    private final Text field;

    private final Func<YamlNode, T> presence;

    private final Scalar<T> absence;

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
    public T value() {
        return new Unchecked<>(
            new Ternary<T>(
                new And(
                    () -> object.asYaml().type().equals(
                        Node.MAPPING
                    ),
                    () -> new KeysFromYamlNode(
                        this.object.asYaml().asMapping()
                    ).contains(field.asString())
                ),
                () -> presence.apply(
                    new YamlNodeOfPath(
                        this.object.asYaml(),
                        this.field
                    )
                ),
                () -> absence.value()
            )
        ).value();
    }

}
