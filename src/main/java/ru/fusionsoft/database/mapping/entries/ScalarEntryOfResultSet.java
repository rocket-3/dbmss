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
package ru.fusionsoft.database.mapping.entries;

import java.sql.ResultSet;
import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;

/**
 * The type of {@link ScalarEntry} ctor shorthand with {@link Query} param specified.
 * @since 0.1
 */
public class ScalarEntryOfResultSet extends ScalarEntry {

    /**
     * Instantiates a new Scalar entry of result set.
     * @param label The {@link T} to be encapsulated.
     * @param query The {@link Query} of {@link T} fields to be encapsulated.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param <T> The {@link Text} subtype, the instances of which to expect in {@link Query}
     */
    public <T extends Text> ScalarEntryOfResultSet(
        final T label,
        final Query<T> query,
        final ResultSet rset
    ) {
        super(label, new TextOfResultSet(label, rset, query));
    }

    /**
     * Instantiates a new Scalar entry of result set.
     * @param label The {@link T} to be encapsulated.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param query The {@link Query} of {@link T} fields to be encapsulated.
     * @param <T> The {@link Text} subtype, the instances of which to expect in {@link Query}
     */
    public <T extends Text> ScalarEntryOfResultSet(
        final T label,
        final ResultSet rset,
        final Query<T> query
    ) {
        this(label, query, rset);
    }

}
