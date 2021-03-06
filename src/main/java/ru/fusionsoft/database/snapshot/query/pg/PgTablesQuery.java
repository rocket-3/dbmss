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

import org.cactoos.scalar.NumberOf;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameDelimiter;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdTableFields}.
 * @since 0.1
 */
public class PgTablesQuery extends PgMessageFormatQuery<DbdTableFields> {

    /**
     * Ctor of PgIndexesQuery with default outcomes.
     * @param dbversion The Number of database version to be encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     * @checkstyle AvoidInlineConditionalsCheck (100 lines)
     * @checkstyle CascadeIndentationCheck (100 lines)
     * @checkstyle LineLengthCheck (100 lines)
     * @checkstyle WhitespaceAroundCheck (100 lines)
     */
    public PgTablesQuery(final Number dbversion) {
        super(
            new TextOfScalar(
                () -> {
                    final boolean partitions = dbversion.intValue() >= new NumberOf("10").intValue();
                    return
                        "SELECT \n"
                        + " schemaname AS {0},\n"
                        + " tablename AS {1},\n"
                        + " tableowner AS {2},\n"
                        + " tablespace AS {3}, \n"
                        + " obj_description(\n"
                        + "    (('\"' || schemaname || '\".\"' || tablename || '\"')::regclass)::oid \n"
                        + " ) AS {4},\n"
                        + " (\n"
                        + "    SELECT "
                        + "        array_agg( distinct n2.nspname || '" + new SimpleObjectNameDelimiter().asString()+ "' || c2.relname )\n"
                        + "        AS dependencies\n"
                        + "    FROM pg_catalog.pg_constraint c \n"
                        + "    JOIN ONLY pg_catalog.pg_class c1     ON c1.oid = c.conrelid\n"
                        + "    JOIN ONLY pg_catalog.pg_class c2     ON c2.oid = c.confrelid\n"
                        + "    JOIN ONLY pg_catalog.pg_namespace n2 ON n2.oid = c2.relnamespace\n"
                        + "    WHERE c.conrelid =(( '\"' || schemaname || '\".\"' || tablename || '\"')::regclass)::oid\n"
                        + "    AND c1.relkind = 'r'\n"
                        + "    AND c.contype = 'f'\n"
                        + " ) AS {5}, \n"
                        + (
                            partitions
                                ? " pg_get_partkeydef((\n"
                                + "    SELECT oid \n"
                                + "    FROM pg_class \n"
                                + "    WHERE relname = tablename \n"
                                + "    AND relnamespace = ("
                                + "select oid from pg_namespace where nspname = schemaname)\n"
                                + " )) AS {6}, \n"
                                + " pg_get_expr(child.relpartbound, child.oid) AS {7}, \n"
                                : " '' AS {6},\n '' AS {7},\n"
                        )
                        + " parentnsp.nspname || '" + new SimpleObjectNameDelimiter().asString() + "' || parent.relname AS {8} \n"
                        + "FROM pg_tables \n"
                        + "LEFT OUTER JOIN pg_inherits \n"
                        + "ON (\n"
                        + "     SELECT oid FROM pg_class \n"
                        + "     WHERE relname = tablename\n"
                        + "     AND relnamespace = (select oid from pg_namespace where nspname = schemaname)\n"
                        + ") = pg_inherits.inhrelid \n"
                        + "LEFT OUTER JOIN pg_class parent ON pg_inherits.inhparent = parent.oid \n"
                        + "LEFT OUTER JOIN pg_class child ON pg_inherits.inhrelid = child.oid \n"
                        + "LEFT OUTER JOIN pg_namespace parentnsp on parentnsp.oid = parent.relnamespace  \n"
                        + "WHERE schemaname not in ('pg_catalog', 'information_schema')";
                }
            ),
            DbdTableFields.SCHEMA,
            DbdTableFields.TABLE,
            DbdTableFields.OWNER,
            DbdTableFields.TABLESPACE,
            DbdTableFields.DESCRIPTION,
            DbdTableFields.DEPENDENCIES,
            DbdTableFields.PARTKEYDEFINITION,
            DbdTableFields.PARTKEYRANGE,
            DbdTableFields.PARENT
        );
    }

}
