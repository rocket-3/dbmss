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
public class ObjectsWithNamesPredicate implements Func<DbObject, Boolean> {

    /**
     * The Set of String of signatures encapsulated.
     */
    private final Set<String> set;

    /**
     * Instantiates a new Objects with names.
     * @param names The Sinatures to be encapsulated.
     */
    public ObjectsWithNamesPredicate(
        final Iterable<ObjectSignature> names
    ) {
        this.set = new SetOf<String>(
            new Mapped<>(
                Text::asString,
                names
            )
        );
    }

    @Override
    public final Boolean apply(final DbObject input) {
        return this.set.contains(input.signature().asString());
    }

}
