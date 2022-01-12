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
package ru.fusionsoft.database.snapshot.objects.signature.name;

import java.sql.ResultSet;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;

/**
 * The shorthand of {@link SimpleObjectName} ctor of
 * {@link ResultSet}, {@link Query} and array of fields to take by them.
 * @since 0.1
 */
public class SimpleObjectNameOfResultSet extends SimpleObjectName {

    /**
     * Instantiates a new {@link SimpleObjectName} of result set.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param query The {@link Query} of {@link T} to be encapsulated.
     * @param fields The {@link T}... to be encapsulated.
     * @param <T> The {@link Text} subtype, the instances of which to expect in {@link Query}
     */
    @SafeVarargs
    public <T extends Text> SimpleObjectNameOfResultSet(
        final ResultSet rset,
        final Query<T> query,
        final T... fields
    ) {
        super(
            new ListOf<Text>(
                new Mapped<>(
                    x -> new TextOfResultSet(x, rset, query),
                    new IterableOf<T>(fields)
                )
            )
        );
    }

}
