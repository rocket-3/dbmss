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
package ru.fusionsoft.database.migration.typecasts;

import java.sql.Connection;
import org.cactoos.Text;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.typecasts.sql.TypecastCheckSql;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfConnection;
import ru.fusionsoft.lib.connection.BooleanOfConnectionExecution;

/**
 * Merging given {@link Typecast}s with given type names diffs, if theres no entry in 'casts', then
 *  {@link Typecast} is created with implicit set to 'false'.
 * @since 0.1
 */
public class TypecastsOfTypeDiffsAndConnection extends IterableEnvelope<Typecast> {

    /**
     * Instantiates a new Typecasts of type diffs and connection.
     * @param server The {@link DbdServerMapping} to be encapsulated.
     * @param checks The Iterable {@link TemporalDiff} of type names to be encapsulated.
     */
    public TypecastsOfTypeDiffsAndConnection(
        final DbdServerMapping server,
        final Iterable<TemporalDiff<Text>> checks
    ) {
        this(new ConnectionOfDbdServerMapping(server), checks);
    }

    /**
     * Instantiates a new Typecasts of type diffs and connection.
     * @param connection The {@link Connection} to be encapsulated.
     * @param checks The Iterable {@link TemporalDiff} of type names to be encapsulated.
     */
    public TypecastsOfTypeDiffsAndConnection(
        final Connection connection,
        final Iterable<TemporalDiff<Text>> checks
    ) {
        super(
            new Unchecked<Iterable<Typecast>>(
                () -> {
                    final DbmsOfConnection dbms = new DbmsOfConnection(connection);
                    return new Mapped<>(
                        check -> new SimpleTypecast(
                            check.current(),
                            check.next(),
                            new BooleanOfConnectionExecution(
                                connection,
                                conn -> {
                                    conn.createStatement().execute(
                                        new TypecastCheckSql(
                                            check.current(),
                                            check.next(),
                                            dbms
                                        ).asString()
                                    );
                                }
                            ).value()
                        ),
                        checks
                    );
                }
            ).value()
        );
    }

}
