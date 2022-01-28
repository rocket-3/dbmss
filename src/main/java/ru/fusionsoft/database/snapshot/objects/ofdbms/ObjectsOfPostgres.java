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
package ru.fusionsoft.database.snapshot.objects.ofdbms;

import java.sql.Connection;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgConstraints;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgDomains;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgEnums;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgFunctions;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgIndexes;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgProcedures;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgSchemas;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgSequences;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgTables;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgTriggers;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgTuples;
import ru.fusionsoft.database.snapshot.objects.ofdbms.pg.PgViews;

/**
 * The Objects of {@link Connection} of Postgres dbms.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ObjectsOfPostgres extends ObjectsOfConnectionJoined {

    /**
     * Ctor.
     * @param connection The wrapped connection.
     */
    public ObjectsOfPostgres(final Connection connection) {
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
