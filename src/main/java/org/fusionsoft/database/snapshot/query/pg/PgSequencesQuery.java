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
package org.fusionsoft.database.snapshot.query.pg;

import org.fusionsoft.database.mapping.fields.DbdSequenceFields;
import org.fusionsoft.database.snapshot.query.MessageFormatQuery;

/**
 * The only type of {@link MessageFormatQuery} of {@link DbdSequenceFields}.
 * @since 0.1
 */
public class PgSequencesQuery extends PgMessageFormatQuery<DbdSequenceFields> {

    /**
     * Instantiates a new Pg sequences query.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    public PgSequencesQuery() {
        super(
            "select \n"
            + "    s.sequence_schema AS {0}, \n"
            + "    s.sequence_name AS {1}, \n"
            + "    rol.rolname AS {2}, \n"
            + "    s.start_value AS {3}, \n"
            + "    s.minimum_value AS {4}, \n"
            + "    s.maximum_value AS {5}, \n"
            + "    s.increment AS {6}, \n"
            + "    s.cycle_option AS {7}, \n"
            + "    cl.relname AS {8} \n"
            + "from pg_class cls \n"
            + "  join pg_roles rol on rol.oid = cls.relowner  \n"
            + "  join pg_namespace nsp on nsp.oid = cls.relnamespace \n"
            + "  join information_schema.sequences s on cls.relname = s.sequence_name \n"
            + "  left join pg_depend d on d.objid=cls.oid and d.classid='pg_class'::regclass \n"
            + "  and d.refclassid='pg_class'::regclass\n"
            + "  left join pg_class cl on cl.oid = d.refobjid and d.deptype='a'  \n"
            + "where nsp.nspname not in ('information_schema', 'pg_catalog')\n"
            + "  and nsp.nspname not like 'pg_toast%' \n"
            + "  and cls.relkind = 'S'",
            DbdSequenceFields.SCHEMA,
            DbdSequenceFields.SEQUENCE,
            DbdSequenceFields.OWNER,
            DbdSequenceFields.START,
            DbdSequenceFields.MIN,
            DbdSequenceFields.MAX,
            DbdSequenceFields.INCREMENT,
            DbdSequenceFields.CYCLE,
            DbdSequenceFields.DEP_TABLE
        );
    }

}
