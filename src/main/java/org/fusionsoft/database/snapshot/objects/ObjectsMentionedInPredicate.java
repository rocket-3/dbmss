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
package org.fusionsoft.database.snapshot.objects;

import java.util.Set;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetOf;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.ObjectSignature;
import org.fusionsoft.database.snapshot.ObjectSignatures;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The type of {@link Objects} that are filtered by {@link ObjectSignatures}.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ObjectsMentionedInPredicate implements Func<DbObject<?>, Boolean> {

    /**
     * The Set of String of signatures encapsulated.
     */
    private final Set<String> names;

    /**
     * Instantiates a new Objects with names.
     * @param names The Sinatures to be encapsulated.
     */
    private ObjectsMentionedInPredicate(
        final Iterable<ObjectSignature> names
    ) {
        this.names = new SetOf<String>(
            new Mapped<>(
                Text::asString,
                names
            )
        );
    }

    /**
     * Instantiates a new Objects with names.
     * @param mentions The objects with names to pass.
     */
    public ObjectsMentionedInPredicate(final Objects mentions) {
        this(new Mapped<ObjectSignature>(DbObject::signature, mentions));
    }

    @Override
    public final Boolean apply(final DbObject input) {
        return this.names.contains(input.signature().asString());
    }

}
