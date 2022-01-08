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

import java.text.MessageFormat;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.fields.DbdColumnFields;
import org.fusionsoft.database.mapping.value.IuTypeValues;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import org.fusionsoft.database.snapshot.query.Query;

/**
 * The type of {@link Query} of {@link DbdColumnFields}
 *  that can be constructed of {@link SimpleObjectName} of table.
 * @since 0.1
 * @checkstyle LineLengthCheck (100 lines)
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 * @checkstyle BracketsStructureCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class PgColumnsQuery extends PgMessageFormatQuery<DbdColumnFields> {

    /**
     * Instantiates a new Pg columns query.
     * @param table The FullObjectName of table to be used in WHERE statement.
     */
    public PgColumnsQuery(final ObjectName table) {
        super(
            new TextOfScalar(() -> MessageFormat.format(
                "SELECT \n"
                + "    col.column_name AS '{0}',\n"
                + "    pgd.description AS '{1}',\n"
                + "    col.column_default AS '{2}',\n"
                + "    case when col.is_nullable in (''NO'') then ''false'' else ''true'' end AS '{3}',\n"
                + "    col.udt_name::regtype AS '{4}',\n"
                + "    col.character_maximum_length,\n"
                + "    col.numeric_precision,\n"
                + "    col.numeric_scale AS scale,\n"
                + "    col.ordinal_position AS '{5}',\n"
                + "    case when pkeys.ispk is null then false else pkeys.ispk end isPk,\n"
                + "    case\n"
                + "        when lower(data_type) in (\n"
                + "            ''integer'', ''numeric'', ''smallint'', ''double precision'',\n"
                + "            ''bigint'', ''oid''\n"
                + "        ) then ''{0}''\n"
                + "        when lower(data_type) in (\n"
                + "            ''character varying'', ''char'', ''character'', ''varchar'',\n"
                + "            ''text'', ''name''\n"
                + "        ) then ''{1}''\n"
                + "        when lower(data_type) in (\n"
                + "            ''timestamp without time zone'', ''timestamp with time zone'',\n"
                + "            ''date''\n"
                + "        ) then ''{2}''\n"
                + "        when lower(data_type) in (''boolean'') then ''{3}''\n"
                + "        when lower(data_type) in (''bytea'') then ''{4}''\n"
                + "        when lower(data_type) in (''array'') then ''{5}''\n"
                + "        when lower(data_type) like ''%json%'' then ''{6}''\n"
                + "        else ''{7}''\n"
                + "    end '{6}', \n"
                + "    case \n"
                + "        when lower(data_type) in (''char'', ''character'') then true\n"
                + "        else false\n"
                + "    end fixed\n"
                + "FROM information_schema.columns col  \n"
                + "left join (\n"
                + "    select \n"
                + "        kc.table_schema,\n"
                + "        kc.table_name,\n"
                + "        kc.column_name,\n"
                + "        bool_or(case when tc.constraint_type = ''PRIMARY KEY'' \n"
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
                + "where upper(col.table_schema) = upper(''{8}'') "
                + "and col.table_name = ''{9}''\n"
                + "order by col.ordinal_position",
                IuTypeValues.NUMBER.asString(),
                IuTypeValues.STRING.asString(),
                IuTypeValues.DATE.asString(),
                IuTypeValues.BOOLEAN.asString(),
                IuTypeValues.BINARY.asString(),
                IuTypeValues.ARRAY.asString(),
                IuTypeValues.JSON.asString(),
                IuTypeValues.NATIVE.asString(),
                table.parent().asString(),
                table.first().asString()
            )),
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
