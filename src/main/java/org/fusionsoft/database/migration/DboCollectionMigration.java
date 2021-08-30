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

/**
 * The type of Migration that migrates persistent collection to target state.
 * @since 0.1
 */
public class DboCollectionMigration implements Migration {

    /**
     * The Iterable Condition encapsulated.
     */
    private final Iterable<Condition> conditions;

    /**
     * The Iterable Migration encapsulated.
     */
    private final Iterable<Migration> migrations;

    /**
     * Instantiates a new Dbo collection migration.
     * @param conditions The Iterable of Condition to be encapsulated.
     * @param migrations The Iterable of Migration to be encapsulated.
     */
    private DboCollectionMigration(
        final Iterable<Condition> conditions,
        final Iterable<Migration> migrations
    ) {
        this.conditions = conditions;
        this.migrations = migrations;
    }

    /**
     * Instantiates a new Dbo collection migration.
     * @param server The Server to be encapsulated.
     * @param persistent The Collection of DbObject to be encapsulated.
     * @param target The Collection of DbObject to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     * @checkstyle ParameterNumberCheck (20 lines)
     */
    public DboCollectionMigration(
        final Server server,
        final Collection<DbObject> persistent,
        final Collection<DbObject> target,
        final RestoreParams params
    ) {
        this(
            new IterableOf<Condition>(
                new EachDboHasDbmsSignature(
                    server.dbmsSignature(),
                    target
                )
            ),
            new IterableOf<Migration>(
                new DboCollectionPreMigration(persistent, server),
                new DboCollectionNoConstraintsMigration(
                    persistent,
                    target,
                    params,
                    server
                ),
                new DboCollectionPostMigration(target, server)
            )
        );
    }

    @Override
    public final boolean validate() throws Exception {
        return new And(
            new And(this.conditions),
            new And(
                Migration::validate,
                this.migrations
            )
        ).value();
    }

    @Override
    public final void perform() throws Exception {
        for (final Migration migration : this.migrations) {
            migration.perform();
        }
    }

}
