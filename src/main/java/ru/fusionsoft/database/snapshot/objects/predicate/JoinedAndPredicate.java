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

import org.cactoos.Func;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import ru.fusionsoft.database.snapshot.DbObject;

/**
 * The Func of DbObject -> Boolean, combined by AND operator.
 * @since 0.1
 */
public class JoinedAndPredicate implements Func<DbObject<?>, Boolean> {

    /**
     * The Iterable of Func of DbObject, Boolean encapsulated.
     */
    private final Iterable<Func<DbObject<?>, Boolean>> funcs;

    /**
     * Instantiates a new Combined and predicate.
     * @param funcs The Iterable of Func of DbObject, Boolean to be encapsulated.
     */
    public JoinedAndPredicate(final Iterable<Func<DbObject<?>, Boolean>> funcs) {
        this.funcs = funcs;
    }

    /**
     * Instantiates a new Combined and predicate.
     * @param funcs The Func of DbObject, Boolean array to be encapsulated.
     */
    @SafeVarargs
    public JoinedAndPredicate(final Func<DbObject<?>, Boolean>... funcs) {
        this(new IterableOf<>(funcs));
    }

    @Override
    public final Boolean apply(final DbObject<?> input) throws Exception {
        return new And(input, this.funcs).value();
    }

}