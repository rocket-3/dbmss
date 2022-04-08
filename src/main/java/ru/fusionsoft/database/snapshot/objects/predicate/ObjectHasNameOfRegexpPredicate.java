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
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameValuesOfText;
import ru.fusionsoft.lib.text.TextMatchesRegexp;

/**
 * The predicate of {@link DbObject}, checking its name matches regexp.
 * @since 0.1
 */
public class ObjectHasNameOfRegexpPredicate implements Func<DbObject<?>, Boolean> {

    /**
     * The Text predicate encapsulated.
     */
    private final UncheckedFunc<Text, Boolean> func;

    /**
     * Instantiates a new Object has name of regexp predicate.
     * @param regex The Regexp {@link Text} to be encapsulated.
     */
    public ObjectHasNameOfRegexpPredicate(final Text regex) {
        this.func = new UncheckedFunc<>(
            new TextMatchesRegexp(regex)
        );
    }

    @Override
    public final Boolean apply(final DbObject<?> input) {
        return this.func.apply(
            new Joined(
                new TextOf("."),
                new SimpleObjectNameValuesOfText(
                    input.signature().name()
                )
            )
        );
    }

}
