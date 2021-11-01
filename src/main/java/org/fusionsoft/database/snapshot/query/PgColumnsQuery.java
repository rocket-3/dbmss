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
package org.fusionsoft.database.snapshot.query;

import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.fields.DbdColumnFields;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;

public class PgColumnsQuery extends PgSimpleQuery<DbdColumnFields> {

    public PgColumnsQuery(final FullObjectName table) {
        super(
            new TextOfScalar(() ->
                "SELECT \n"
                + "    col.column_name AS {0},\n"
                + "    pgd.description AS {1},\n"
                + "    col.column_default AS {2},\n"
                + "    case when col.is_nullable in ('NO') then 'false' else 'true' end AS {3},\n"
                + "    col.udt_name::regtype AS {4},\n"
                + "    col.character_maximum_length,\n"
                + "    col.numeric_precision,\n"
                + "    col.numeric_scale AS scale,\n"
                + "    col.ordinal_position AS {5},\n"
                + "    case when pkeys.ispk is null then false else pkeys.ispk end isPk,\n"
                + "    case\n"
                + "        when lower(data_type) in ('integer', 'numeric', 'smallint', 'double precision', 'bigint', 'oid') then 'number' \n"
                + "        when lower(data_type) in ('character varying', 'char', 'character', 'varchar', 'text', 'name') then 'string'\n"
                + "        when lower(data_type) in ('timestamp without time zone', 'timestamp with time zone', 'date') then 'date'\n"
                + "        when lower(data_type) in ('boolean') then 'boolean'\n"
                + "        when lower(data_type) in ('bytea') then 'binary'\n"
                + "        when lower(data_type) in ('array') then 'array'\n"
                + "        when lower(data_type) like '%json%' then 'json'\n"
                + "        else 'native'\n"
                + "    end {6}, \n"
                + "    case when lower(data_type) in ('char', 'character') then true else false \n"
                + "    end fixed\n"
                + "FROM information_schema.columns col  \n"
                + "left join (\n"
                + "    select \n"
                + "        kc.table_schema,\n"
                + "        kc.table_name,\n"
                + "        kc.column_name,\n"
                + "        bool_or(case when tc.constraint_type = 'PRIMARY KEY' \n"
                + "            then true \n"
                + "            else false end \n"
                + "        ) ispk\n"
                + "    from information_schema.table_constraints tc\n"
                + "    inner join information_schema.key_column_usage kc \n"
                + "    on kc.constraint_name = tc.constraint_name\n"
                + "    group by kc.table_schema, kc.table_name, kc.column_name\n"
                + ") pkeys on pkeys.table_schema = col.table_schema"
                + " and pkeys.table_name = col.table_name"
                + " and pkeys.column_name = col.column_name\n"
                + "left join pg_catalog.pg_statio_all_tables st"
                + " on st.schemaname = col.table_schema"
                + " and st.relname = col.table_name \n"
                + "left join pg_catalog.pg_description pgd"
                + " on (pgd.objoid=st.relid and pgd.objsubid=col.ordinal_position) \n"
                + "where upper(col.table_schema) = upper('"
                + table.parent()
                + "') "
                + "and col.table_name = '"
                + table.first()
                + "'\n"
                + "order by col.ordinal_position"
            ),
            DbdColumnFields.DBNAME,
            DbdColumnFields.DESCRIPTION,
            DbdColumnFields.DEFAULT,
            DbdColumnFields.DBNULLABLE,
            DbdColumnFields.DBMSTYPE,
            DbdColumnFields.ORDER,
            DbdColumnFields.IUTYPE
        );
    }

}
