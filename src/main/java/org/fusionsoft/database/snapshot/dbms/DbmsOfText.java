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
package org.fusionsoft.database.snapshot.dbms;

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;

/**
 * The type of {@link Dbms} that can be constructed of Scalar.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbmsOfText extends DbmsOfScalar {

    /**
     * Instantiates a new Dbms of text.
     * @param text The Text to be searched.
     */
    public DbmsOfText(final Text text) {
        super(
            () -> new MapOf<String, Dbms>(
                new Mapped<>(
                    x -> new MapEntry<>(x.asString(), x),
                    new IterableOf<Dbms>(
                        new PostgresDbms(),
                        new MySqlDbms(),
                        new OracleDbms(),
                        new MsSqlDbms()
                    )
                )
            ).get(text.asString())
        );
    }

}
