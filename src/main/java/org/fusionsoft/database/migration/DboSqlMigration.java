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
import org.cactoos.Text;
import org.cactoos.scalar.And;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.condition.DbObjectsHasSameDbmsSignature;

/**
 * The type of Migration pair of DbObjects
 * that can be performed by passing SQL to consumer.
 * @since 0.1
 */
@SuppressWarnings("PMD.UnusedFormalParameter")
public class DboSqlMigration extends SqlMigration {

    /**
     * Instantiates a new Dbo sql migration.
     * @param pair The DiffPair of DbObject to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     * @param consumer The Proc of Text to be encapsulated.
     */
    public DboSqlMigration(
        final DiffPair<DbObject> pair,
        final RestoreParams params,
        final Proc<Text> consumer
    ) {
        super(
            new TextOf(""),
            consumer,
            new And(
                () -> true,
                new DbObjectsHasSameDbmsSignature(pair)
            )
        );
    }

}
