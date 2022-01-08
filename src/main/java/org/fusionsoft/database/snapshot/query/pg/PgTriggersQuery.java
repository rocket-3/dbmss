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
package org.fusionsoft.database.snapshot.query.pg;

import org.fusionsoft.database.mapping.fields.DbdTriggerFields;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdTriggerFields}.
 * @since 0.1
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
public class PgTriggersQuery extends PgMessageFormatQuery<DbdTriggerFields> {

    /**
     * Instantiates a new Pg triggers query.
     */
    public PgTriggersQuery() {
        super(
            "SELECT \n"
            + " ns.nspname AS {0},\n"
            + " tbl.relname AS {1},\n"
            + " trg.tgname AS {2},\n"
            + " pg_get_triggerdef(trg.oid) AS {3} \n"
            + "FROM pg_trigger trg\n"
            + "JOIN pg_class tbl on trg.tgrelid = tbl.oid\n"
            + "JOIN pg_namespace ns ON ns.oid = tbl.relnamespace\n"
            + "and trg.tgconstraint=0",
            DbdTriggerFields.SCHEMA,
            DbdTriggerFields.TABLE,
            DbdTriggerFields.TRIGGER,
            DbdTriggerFields.DDL
        );
    }

}
