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

import org.cactoos.Text;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.mapping.fields.DbdProcedureFields;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdProcedureFields}.
 * @since 0.1
 */
public class PgProceduresQuery extends PgMessageFormatQuery<DbdProcedureFields> {

    /**
     * Instantiates a new Pg procedures query.
     * @param dbmsversion The Number of DBMS used to be encapsulated.
     * @checkstyle MagicNumberCheck (16 lines)
     */
    public PgProceduresQuery(final Number dbmsversion) {
        this(
            new TextOfScalar(
                new Ternary<CharSequence>(
                    () -> dbmsversion.intValue() >= 11,
                    () -> "p.prokind = 'p'",
                    () -> "1=0"
                )
            )
        );
    }

    /**
     * Instantiates a new Pg procedures query.
     * @param filter The Text of WHERE filter to be encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    public PgProceduresQuery(final Text filter) {
        super(
            new TextOfScalar(
                () ->
                    "SELECT \n"
                    + " n.nspname AS {0}, \n"
                    + " u.rolname AS {1}, \n"
                    + " p.proname AS {2}, \n"
                    + " pg_catalog.pg_get_function_arguments(p.oid) AS {3},\n"
                    + " pg_get_functiondef(p.oid) AS {4}\n"
                    + "FROM pg_catalog.pg_proc p\n"
                    + "JOIN pg_catalog.pg_roles u ON u.oid = p.proowner\n"
                    + "LEFT JOIN pg_catalog.pg_namespace n ON n.oid = p.pronamespace\n"
                    + "WHERE " + filter.asString() + "\n"
                    + "AND n.nspname not in('pg_catalog', 'information_schema')"
            ),
            DbdProcedureFields.SCHEMA,
            DbdProcedureFields.OWNER,
            DbdProcedureFields.PROCEDURE,
            DbdProcedureFields.ARGUMENTS,
            DbdProcedureFields.DDL
        );
    }

}
