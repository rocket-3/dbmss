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
package org.fusionsoft.database.migration;

import org.cactoos.Proc;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.fusionsoft.database.Migration;

/**
 * The type of Migration
 * that can be performed by passing SQL to consumer.
 * @since 0.1
 */
public abstract class SqlMigration implements Migration {

    /**
     * The Text encapsulated.
     */
    private final Text sql;

    /**
     * The Proc of Text encapsulated.
     */
    private final Proc<Text> consumer;

    /**
     * The Scalar of Boolean encapsulated.
     */
    private final Scalar<Boolean> validation;

    /**
     * Instantiates a new Sql migration.
     * @param sql The Text to be encapsulated.
     * @param consumer The Proc of Text to be encapsulated.
     * @param validation The Scalar of Boolean to be encapsulated.
     */
    public SqlMigration(
        final Text sql,
        final Proc<Text> consumer,
        final Scalar<Boolean> validation
    ) {
        this.sql = sql;
        this.consumer = consumer;
        this.validation = validation;
    }

    @Override
    public final boolean validate() throws Exception {
        return this.validation.value();
    }

    @Override
    public final void perform() throws Exception {
        this.consumer.exec(this.sql);
    }

}
