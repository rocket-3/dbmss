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
import org.cactoos.list.ListOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.RestoreParams;
import org.fusionsoft.database.Server;
import org.fusionsoft.database.diffpair.DbObjectDiffPairs;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The type of Migration that migrates database through
 * collection of objects difference between current and previous state,
 * assuming there's no table constraints exists at the moment.
 * @since 0.1
 */
public class DboCollectionNoConstraintsMigration implements Migration {

    /**
     * The Collection of DiffPair of DbObject encapsulated.
     */
    private final Collection<DiffPair<DbObject>> diff;

    /**
     * The RestoreParams encapsulated.
     */
    private final RestoreParams params;

    /**
     * The Server encapsulated.
     */
    private final Server server;

    /**
     * Instantiates a new Dbo collection no constraints migration.
     * @param persistent The Collection of DbObject to be encapsulated.
     * @param target The Collection of DbObject to be encapsulated.
     * @param params The RestoreParams to be encapsulated.
     * @param server The Server to be encapsulated.
     * @checkstyle ParameterNumberCheck (10 lines).
     */
    public DboCollectionNoConstraintsMigration(
        final Collection<DbObject> persistent,
        final Collection<DbObject> target,
        final RestoreParams params,
        final Server server
    ) {
        this.diff = new ListOf<>(
            new DbObjectDiffPairs(persistent, target)
        );
        this.params = params;
        this.server = server;
    }

    @Override
    public final boolean validate() {
        throw new NotImplemented();
    }

    @Override
    public final void perform() {
        this.diff.notifyAll();
        this.params.notifyAll();
        this.server.notifyAll();
        throw new NotImplemented();
    }

}
