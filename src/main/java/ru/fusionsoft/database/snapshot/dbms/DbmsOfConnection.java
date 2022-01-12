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
package ru.fusionsoft.database.snapshot.dbms;

import java.sql.Connection;
import java.util.Locale;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.lib.collection.Single;

/**
 * The type of {@link Dbms} that can be constructed of {@link Connection}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DbmsOfConnection extends DbmsOfScalar {

    /**
     * Instantiates a new Dbms of scalar.
     * @param connection The Connection to Dbms to be used.
     */
    public DbmsOfConnection(final Connection connection) {
        super(
            new Single<Dbms>(
                new Filtered<Dbms>(
                    dbms -> connection
                        .getMetaData()
                        .getDatabaseProductName()
                        .toLowerCase(Locale.ENGLISH)
                        .contains(
                            dbms.driver().asString().toLowerCase(Locale.ENGLISH)
                        ),
                    new IterableOf<Dbms>(
                        new PostgresDbms(),
                        new MySqlDbms(),
                        new OracleDbms(),
                        new MsSqlDbms()
                    )
                )
            )
        );
    }

}
