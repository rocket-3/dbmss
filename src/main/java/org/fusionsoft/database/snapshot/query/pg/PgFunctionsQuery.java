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

import org.cactoos.Text;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.fields.DbdFunctionFields;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdFunctionFields}.
 * @since 0.1
 */
public class PgFunctionsQuery extends PgMessageFormatQuery<DbdFunctionFields> {

    /**
     * Instantiates a new Pg functions query.
     * @param dbmsversion The Number to be encapsulated.
     * @checkstyle MagicNumberCheck (16 lines)
     */
    public PgFunctionsQuery(final Number dbmsversion) {
        this(
            new TextOfScalar(
                new Ternary<>(
                    () -> dbmsversion.intValue() >= 10,
                    () -> "p.prokind IN('a')",
                    () -> "p.proisagg"
                )
            ),
            new TextOfScalar(
                new Ternary<>(
                    () -> dbmsversion.intValue() >= 10,
                    () -> "p.prokind IN ('a', 'f')",
                    () -> "1=1"
                )
            )
        );
    }

    /**
     * Instantiates a new Pg functions query.
     * @param aggregate The Text to be encapsulated.
     * @param filter The Text to be encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     * @checkstyle BracketsStructureCheck (100 lines)
     */
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    public PgFunctionsQuery(final Text aggregate, final Text filter) {
        super(
            new TextOfScalar(() ->
                "SELECT \n"
                + " n.nspname AS {0}, \n"
                + " u.rolname AS {1}, \n"
                + " p.proname AS {2},  \n"
                + " pg_catalog.pg_get_function_arguments(p.oid) AS {3}, \n"
                + " CASE WHEN "
                + aggregate.asString()
                + "\n"
                + "    THEN format(\n"
                + "        E'CREATE AGGREGATE %s (\\n%s\\n);', \n"
                + "        (pg_identify_object('pg_proc'::regclass, aggfnoid, 0)).identity, \n"
                + "        array_to_string(\n"
                + "            ARRAY[\n"
                + "                format(E'\\tSFUNC = %s', aggtransfn::regproc), \n"
                + "                format(E'\\tSTYPE = %s', format_type(aggtranstype, NULL)), \n"
                + "                CASE aggfinalfn \n"
                + "                  WHEN '-'::regproc THEN NULL \n"
                + "                  ELSE format(E'\\tFINALFUNC = %s',aggfinalfn::text) END, \n"
                + "                CASE aggsortop "
                + "                  WHEN 0 THEN NULL "
                + "                  ELSE format(E'\\tSORTOP = %s', oprname) END,\n"
                + "                CASE WHEN agginitval IS NULL THEN NULL \n"
                + "                  ELSE format(E'\\tINITCOND = %s', agginitval) END\n"
                + "            ], E',\\n'\n"
                + "        )\n"
                + "    ) \n"
                + "    ELSE pg_get_functiondef(p.oid) \n"
                + " END AS {4}, \n"
                + " CASE WHEN "
                + aggregate.asString()
                + " THEN 'true' ELSE 'false' END AS {5} \n"
                + "FROM pg_catalog.pg_proc p \n"
                + "    JOIN pg_catalog.pg_roles u ON u.oid = p.proowner \n"
                + "    LEFT JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace\n"
                + "    LEFT JOIN pg_aggregate a ON a.aggfnoid = p.oid \n"
                + "    LEFT JOIN pg_operator op ON op.oid = a.aggsortop \n"
                + "WHERE "
                + filter.asString()
                + "\n"
                + "AND n.nspname not in('pg_catalog', 'information_schema') "
            ),
            DbdFunctionFields.SCHEMA,
            DbdFunctionFields.OWNER,
            DbdFunctionFields.FUNCTION,
            DbdFunctionFields.ARGUMENTS,
            DbdFunctionFields.DDL,
            DbdFunctionFields.AGGREGATE
        );
    }

}
