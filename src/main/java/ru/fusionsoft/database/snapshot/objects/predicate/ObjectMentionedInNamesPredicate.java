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
package ru.fusionsoft.database.snapshot.objects.predicate;

import java.util.Set;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;

/**
 * The predicate of {@link DbObject}, checks being mentioned in {@link ObjectSignature}s list.
 * @since 0.1
 */
public class ObjectMentionedInNamesPredicate implements Func<DbObject<?>, Boolean> {

    /**
     * The Set of String of signatures encapsulated.
     */
    private final Unchecked<Set<String>> names;

    /**
     * Instantiates a new Objects with names.
     * @param names The Sinatures to be encapsulated.
     */
    public ObjectMentionedInNamesPredicate(
        final Iterable<ObjectSignature> names
    ) {
        this.names = new Unchecked<>(
            new Sticky<>(
                () -> new SetOf<String>(
                    new Mapped<>(
                        Text::asString,
                        names
                    )
                )
            )
        );
    }

    @Override
    public final Boolean apply(final DbObject input) {
        return this.names.value().contains(input.signature().asString());
    }

}
