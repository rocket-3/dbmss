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
package ru.fusionsoft.database.snapshot.query.pg;

import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdConstraintFields}.
 * @since 0.1
 * @checkstyle StringLiteralsConcatenationCheck (128 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PgConstraintsQuery extends PgMessageFormatQuery<DbdConstraintFields> {

    /**
     * Instantiates a new Pg constraints query.
     */
    public PgConstraintsQuery() {
        super(
            String.join(
                "",
                "SELECT \n",
                "    nsp.nspname AS {0},\n",
                "    rel.relname AS {1},\n",
                "    con.conname AS {2},\n",
                "    t.tableowner AS {3},\n",
                "    f_sch.nspname AS {4},\n",
                "    case \n",
                "        when confrelid::regclass::text = '-' then null \n",
                "        else confrelid::regclass \n",
                "    end AS {5},\n",
                "    case \n",
                "        when con.contype = 'p' then '" + ConstraintTypeValues.PK + "'\n",
                "        when con.contype = 'f' then '" + ConstraintTypeValues.FK + "'\n",
                "        when con.contype = 'c' then '" + ConstraintTypeValues.CHECK + "'\n",
                "        when con.contype = 'u' then '" + ConstraintTypeValues.UNIQUE + "'\n",
                "        when con.contype = 'x' then '" + ConstraintTypeValues.EXCLUDE + "'\n",
                "    end AS {6},\n",
                "    array_agg(ta.attname) AS {7},\n",
                "    case \n",
                "        when array_remove(array_agg(fa.attname), NULL) = '{}' then null \n",
                "        else array_agg(fa.attname) \n",
                "    end AS {8},\n",
                "    case \n",
                "        when con.confupdtype = 'c' then 'CASCADE'\n",
                "        when con.confupdtype = 'a' then 'NO ACTION'\n",
                "        when con.confupdtype = 'r' then 'RESTRICT'\n",
                "        when con.confupdtype = 'n' then 'SET NULL'\n",
                "        when con.confupdtype = 'd' then 'SET DEFAULT'\n",
                "        else ''\n",
                "    end AS {9},\n",
                "    case\n",
                "        when con.confdeltype = 'c' then 'CASCADE'\n",
                "        when con.confdeltype = 'a' then 'NO ACTION'\n",
                "        when con.confdeltype = 'r' then 'RESTRICT'\n",
                "        when con.confdeltype = 'n' then 'SET NULL'\n",
                "        when con.confdeltype = 'd' then 'SET DEFAULT'\n",
                "        else ''\n",
                "    end AS {10},\n",
                "    pg_catalog.pg_get_constraintdef(con.oid, true) AS {11}\n",
                "FROM (\n",
                "   SELECT\n",
                "    conname, contype, confrelid, conrelid, connamespace,\n",
                "    confupdtype, confdeltype, oid,\n",
                "    unnest(conkey) AS conkey, unnest(confkey) AS confkey\n",
                "   FROM pg_constraint\n",
                ") con\n",
                "INNER JOIN pg_catalog.pg_class rel ON rel.oid = con.conrelid\n",
                "INNER JOIN pg_catalog.pg_namespace nsp ON nsp.oid = connamespace\n",
                "INNER JOIN pg_tables t ON nsp.nspname = t.schemaname ",
                "AND rel.relname = t.tablename\n",
                "LEFT OUTER JOIN pg_attribute AS ta ON ta.attrelid = conrelid ",
                "AND ta.attnum = conkey\n",
                "LEFT OUTER JOIN pg_attribute AS fa ON con.contype = 'f' ",
                "AND fa.attrelid = confrelid AND fa.attnum = confkey\n",
                "LEFT JOIN pg_class f_tbl ON f_tbl.oid = con.confrelid\n",
                "LEFT JOIN pg_namespace f_sch ON f_sch.oid = f_tbl.relnamespace\n",
                "GROUP BY 1, 2, 3, 4, 5, 6, 7, 10, 11, 12"
            ),
            DbdConstraintFields.SCHEMA,
            DbdConstraintFields.TABLE,
            DbdConstraintFields.CONSTRAINT,
            DbdConstraintFields.OWNER,
            DbdConstraintFields.TGT_SCHEMA,
            DbdConstraintFields.TGT_TABLE,
            DbdConstraintFields.TYPE,
            DbdConstraintFields.SRC_PK_COL,
            DbdConstraintFields.TGT_COL,
            DbdConstraintFields.ON_UPDATE,
            DbdConstraintFields.ON_DELETE,
            DbdConstraintFields.DDL
        );
    }

}
