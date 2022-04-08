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

import ru.fusionsoft.database.mapping.fields.DbdViewFields;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameDelimiter;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdViewFields}.
 * @since 0.1
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
public class PgViewsQuery extends PgMessageFormatQuery<DbdViewFields> {

    /**
     * Instantiates a new Pg views query.
     */
    public PgViewsQuery() {
        super(
            "SELECT \n"
            + " nsp.nspname AS {0}, \n"
            + " cls.relname AS {1},\n"
            + " rol.rolname AS {2}, \n"
            + " 'create or replace view '\n"
            + " || nsp.nspname\n"
            + " || '.'\n"
            + " || cls.relname\n"
            + " || ' as '\n"
            + " || pg_get_viewdef(cls.oid) AS {3},\n"
            + " (\n"
            + "    select array_agg(\n"
            + "        distinct source_ns.nspname || "
            + "'" + new SimpleObjectNameDelimiter().asString() + "'"
            + " || source_table.relname\n"
            + "    ) as dependencySam\n"
            + "    from pg_depend \n"
            + "    join pg_rewrite on pg_depend.objid = pg_rewrite.oid \n"
            + "    join pg_class as dependent_view on pg_rewrite.ev_class = dependent_view.oid \n"
            + "    join pg_class as source_table \n"
            + "        on pg_depend.refobjid = source_table.oid \n"
            + "        and source_table.relkind = 'v'\n"
            + "    join pg_attribute \n"
            + "        on pg_attribute.attrelid  = pg_depend.refobjid \n"
            + "        and pg_attribute.attnum = pg_depend.refobjsubid  \n"
            + "    join pg_namespace dependent_ns \n"
            + "        on dependent_ns.oid = dependent_view.relnamespace\n"
            + "    join pg_namespace source_ns on source_ns.oid = source_table.relnamespace\n"
            + "    where pg_attribute.attnum > 0 \n"
            + "    and dependent_view.relname = cls.relname\n"
            + ") as {4}\n"
            + "FROM pg_class cls  \n"
            + "JOIN pg_roles rol on rol.oid = cls.relowner \n"
            + "JOIN pg_namespace nsp on nsp.oid = cls.relnamespace  \n"
            + "WHERE nsp.nspname not in ('information_schema', 'pg_catalog')  \n"
            + "AND cls.relname not in ('pg_buffercache', 'pg_stat_statements') \n"
            + "AND nsp.nspname not like 'pg_toast%' \n"
            + "AND cls.relkind = 'v' \n",
            DbdViewFields.SCHEMA,
            DbdViewFields.VIEW,
            DbdViewFields.OWNER,
            DbdViewFields.DDL,
            DbdViewFields.DEPENDENCIES
        );
    }

}
