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
package org.fusionsoft.database.snapshot.objects.ofdbms;

import java.sql.Connection;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgConstraints;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgDomains;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgEnums;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgFunctions;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgIndexes;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgProcedures;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgSchemas;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgSequences;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgTables;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgTriggers;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgTuples;
import org.fusionsoft.database.snapshot.objects.ofdbms.postgres.PgViews;

/**
 * The Objects of {@link Connection} of Postgres dbms.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ObjectsFromPostgres extends ObjectsOfConnectionJoined {

    /**
     * Ctor.
     * @param connection The wrapped connection.
     */
    public ObjectsFromPostgres(final Connection connection) {
        super(
            connection,
            PgSchemas::new,
            PgTables::new,
            PgIndexes::new,
            PgConstraints::new,
            PgSequences::new,
            PgViews::new,
            PgFunctions::new,
            PgProcedures::new,
            PgTriggers::new,
            PgDomains::new,
            PgTuples::new,
            PgEnums::new
        );
    }

}
