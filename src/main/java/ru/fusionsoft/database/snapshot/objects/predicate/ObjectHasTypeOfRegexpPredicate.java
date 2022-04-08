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
import org.cactoos.Text;
import org.cactoos.func.UncheckedFunc;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.text.TextMatchesRegexp;

/**
 * The predicate of {@link DbObject} that checks its typename matches Regexp.
 * @since 0.1
 */
public class ObjectHasTypeOfRegexpPredicate implements Func<DbObject<?>, Boolean> {

    /**
     * The predicate of Text encapsulated.
     */
    private final UncheckedFunc<Text, Boolean> func;

    /**
     * Instantiates a new Object has type of regexp predicate.
     * @param func The predicate of Text encapsulated.
     */
    private ObjectHasTypeOfRegexpPredicate(final Func<Text, Boolean> func) {
        this.func = new UncheckedFunc<>(func);
    }

    /**
     * Instantiates a new Object has type of regexp predicate.
     * @param regexp The Regexp {@link Text} to be encapsulated.
     */
    public ObjectHasTypeOfRegexpPredicate(final Text regexp) {
        this(new TextMatchesRegexp(regexp));
    }

    @Override
    public final Boolean apply(final DbObject<?> input) {
        return this.func.apply(input.signature().type());
    }

}
