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

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldMapped;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfText;
import ru.fusionsoft.lib.yaml.artefacts.StringSetOfYamlSequence;

/**
 * The {@link ObjectSignature}'s Iterable
 *  of {@link DbObject} dependencies from representation field.
 * @since 0.1
 */
@SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
public class SignaturesOfObjectDependencies extends IterableEnvelope<ObjectName> {

    /**
     * Instantiates a new Names of object dependencies.
     * @param object The {@link DbObject} to be encapsulated.
     */
    public SignaturesOfObjectDependencies(final DbObject<?> object) {
        super(
            new IterableOf<>(
                () -> new ObjectFieldMapped<Iterable<ObjectName>>(
                    object,
                    DbdTableFields.DEPENDENCIES,
                    node -> new Mapped<>(
                        name -> new SimpleObjectNameOfText(new TextOf(name)),
                        new StringSetOfYamlSequence(node)
                    ),
                    IterableOf<ObjectName>::new
                ).value().iterator()
            )
        );
    }

}
