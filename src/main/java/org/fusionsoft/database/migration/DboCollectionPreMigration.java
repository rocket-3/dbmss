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

//want to convert to PersistentDbObjectsWithoutConstraints extends Collection<DbObject>
public class DboCollectionPreMigration implements Migration {

    private final Collection<DbObject> persistentDbObjects;

    private final Server server;

    public DboCollectionPreMigration(final Collection<DbObject> persistentDbObjects, final Server server) {
        this.persistentDbObjects = persistentDbObjects;
        this.server = server;
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void perform() {

    }

}
