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
package ru.fusionsoft.database.migration.common;

import org.cactoos.Scalar;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.MigrationOfScalarSticky;
import ru.fusionsoft.database.migration.postgres.PgConstraintDropMigration;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.dbms.PostgresDbms;
import ru.fusionsoft.lib.collection.StrictMap;

public class ConstraintDropMigration extends MigrationOfScalarSticky {

    public ConstraintDropMigration(final DbObject<DbdConstraintMapping> constraint, final Dbms dbms) {
        super(
            () -> {
                return new StrictMap<>(
                    new MapOf<>(
                        new MapEntry<Dbms, Scalar<Migration>>(
                            new PostgresDbms(),
                            () -> new PgConstraintDropMigration(constraint)
                        )
                    )
                ).get(dbms).value();
            }
        );
    }

}
