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
package org.fusionsoft.database.snapshot.objects.dbms.postgres;

import java.sql.Connection;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsOfScalar;

/**
 * The type of {@link Objects} that can be constructed of connection to Postgres DBMS.
 * @since 0.1
 * @todo #101:30min Adapt query for UDT's (tuples) from DbGit.
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
@SuppressWarnings("PMD")
public class PgTuples extends ObjectsOfScalar {

    /**
     * Instantiates a new Postgres tuples (UDT's) .
     * @param connection The Connection to be encapsulated.
     */
    public PgTuples(final Connection connection) {
        super(
            () -> new IterableOf<>()
        );
    }

}
