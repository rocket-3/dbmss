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

public class DboCollectionPostMigration implements Migration {

    private final Collection<DbObject> targetDbObjects;

    private final Server server;

    public DboCollectionPostMigration(final Collection<DbObject> targetDbObjects, final Server server) {
        this.targetDbObjects = targetDbObjects;
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
