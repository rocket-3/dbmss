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
package ru.fusionsoft.lib.connection;

import java.sql.Connection;
import java.sql.SQLException;
import org.cactoos.Fallback;
import org.cactoos.Proc;
import org.cactoos.Scalar;
import org.cactoos.scalar.ScalarWithFallback;
import org.cactoos.scalar.Unchecked;

/**
 * Boolean value of given procedure of given connection is not failed and txn rollbacked.
 * @since 0.1
 */
public class BooleanOfConnectionExecution implements Scalar<Boolean> {

    /**
     * The Connection encapsulated.
     */
    private final Connection connection;

    /**
     * The {@link Proc} of {@link Connection} encapsulated.
     */
    private final Proc<Connection> execution;

    /**
     * Instantiates a new Boolean of connection execution.
     * @param connection The {@link Connection} to be encapsulated.
     * @param execution The {@link Proc} of {@link Connection},
     *  which would be executed and may throw exception.
     */
    public BooleanOfConnectionExecution(
        final Connection connection,
        final Proc<Connection> execution
    ) {
        this.connection = connection;
        this.execution = execution;
    }

    @Override
    public final Boolean value() {
        final boolean auto = new Unchecked<>(this.connection::getAutoCommit).value();
        return new Unchecked<>(
            new ScalarWithFallback<>(
                () -> {
                    this.connection.setAutoCommit(false);
                    this.execution.exec(this.connection);
                    this.connection.commit();
                    this.connection.setAutoCommit(auto);
                    return true;
                },
                new Fallback.From<>(
                    SQLException.class,
                    ex -> {
                        this.connection.rollback();
                        this.connection.setAutoCommit(auto);
                        return false;
                    }
                )
            )
        ).value();
    }

}
