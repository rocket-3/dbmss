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

import java.util.Collection;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import org.fusionsoft.database.Condition;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.condition.EachDboHasDbmsSignature;

public class DboCollectionMigration implements Migration {

    private final Iterable<Condition> conditions;

    private final Iterable<Migration> migrations;

    private DboCollectionMigration(final Iterable<Condition> conditions, final Iterable<Migration> migrations) {
        this.conditions = conditions;
        this.migrations = migrations;
    }

    public DboCollectionMigration(
        final Server server,
        final Collection<DbObject> persistentDBOs,
        final Collection<DbObject> targetDBOs,
        final RestoreParams restoreParams
    ) {
        this(
            new IterableOf<Condition>(
                new EachDboHasDbmsSignature(
                    server.dbmsSignature(),
                    targetDBOs
                )
            ),
            new IterableOf<Migration>(
                new DboCollectionPreMigration(persistentDBOs, server),
                new DboCollectionNoConstraintsMigration(
                    persistentDBOs,
                    targetDBOs,
                    restoreParams,
                    server
                ),
                new DboCollectionPostMigration(targetDBOs, server)
            )
        );
    }

    @Override
    public boolean validate() throws Exception {
        return new And(
            new And(this.conditions),
            new And(
                Migration::validate,
                migrations
            )
        ).value();
    }

    @Override
    public void perform() throws Exception {
        for (final Migration migration : this.migrations) {
            migration.perform();
        }
    }

}
