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

public abstract class SqlMigration implements Migration {

    private final Text sql;

    private final Proc<Text> sqlConsumer;

    private final Scalar<Boolean> validation;

    public SqlMigration(final Text sql, final Proc<Text> sqlConsumer, final Scalar<Boolean> validation) {
        this.sql = sql;
        this.sqlConsumer = sqlConsumer;
        this.validation = validation;
    }

    @Override
    public final boolean validate() throws Exception {
        return this.validation.value();
    }

    @Override
    public final void perform() throws Exception {
        sqlConsumer.exec(sql);
    }

}
