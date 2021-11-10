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
package org.fusionsoft.database.snapshot.objectsignature;

import java.sql.ResultSet;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;

public class FullObjectNameOfResultSet extends FullObjectName {

    @SafeVarargs
    public <T extends Text> FullObjectNameOfResultSet(
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
