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
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.Migration;
import org.fusionsoft.database.Server;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The type of Migration that migrates database through
 * collection of objects difference between current and previous state,
 * restoring constraints for tables.
 * @since 0.1
 */
public class DboCollectionPostMigration implements Migration {

    /**
     * The Collection of DbObject encapsulated.
     */
    private final Collection<DbObject> target;

    /**
     * The Server encapsulated.
     */
    private final Server server;

    /**
     * Instantiates a new Dbo collection post migration.
     * @param target The Collection of DbObject to be encapsulated.
     * @param server The Server to be encapsulated.
     */
    public DboCollectionPostMigration(
        final Collection<DbObject> target,
        final Server server
    ) {
        this.target = target;
        this.server = server;
    }

    @Override
    public final boolean validate() {
        this.target.notifyAll();
        this.server.notifyAll();
        throw new NotImplemented();
    }

    @Override
    public final void perform() {
        this.target.notifyAll();
        this.server.notifyAll();
        throw new NotImplemented();
    }

}
