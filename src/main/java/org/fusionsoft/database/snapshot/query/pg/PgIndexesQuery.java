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

import java.text.MessageFormat;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.mapping.fields.DbdIndexFields;
import org.fusionsoft.database.snapshot.query.BasicQuery;

/**
 * The type of that can be constructed of.
 * @since 0.1
 * @checkstyle MagicNumberCheck (100 lines)
 */
public class PgIndexesQuery extends BasicQuery<DbdIndexFields> {

    /**
     * Ctor of PgIndexesQuery with default outcomes.
     */
    public PgIndexesQuery() {
        this(
            "schema",
            "table",
            "index",
            "unique",
            "owner",
            "tspace",
            "indtype",
            "ddl",
            "cols"
        );
    }

    /**
     * Ctor.
     * @param outcomes The outcomes array to be encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    private PgIndexesQuery(final String... outcomes) {
        super(
            MessageFormat.format(
                "select \n"
                + "       ixs.schemaname       AS {0},\n"
                + "       ixs.tablename        AS {1},\n"
                + "       i.relname            AS {2},\n"
                + "       ix.indisunique       AS {3},\n"
                + "       t.tableowner         AS {4},\n"
                + "       ixs.tablespace       AS {5},\n"
                + "       tps.amname           AS {6},\n"
                + "       ixs.indexdef         AS {7},\n"
                + "       array_agg(a.attname) AS {8}\n"
                + "from pg_class c\n"
                + "       inner join pg_index  ix ON c.oid=ix.indrelid\n"
                + "       inner join pg_class  i ON ix.indexrelid=i.oid\n"
                + "       inner join pg_attribute a\n"
                + "         ON a.attrelid=c.oid\n"
                + "         AND a.attnum=any(ix.indkey)\n"
                + "       join pg_catalog.pg_am tps ON tps.oid = i.relam\n"
                + "       join pg_indexes AS ixs ON ixs.indexname = i.relname\n"
                + "       join pg_tables AS t\n"
                + "         ON ixs.schemaname = t.schemaname\n"
                + "         AND ixs.tablename = t.tablename\n"
                + "WHERE ixs.tablename not like ''pg%''\n"
                + "group by 1, 2, 3, 4, 5, 6, 7, 8\n"
                + "order by i.relname",
                (Object[]) outcomes
            ),
            new MapOf<>(
                new MapEntry<>(DbdIndexFields.SCHEMA, outcomes[0]),
                new MapEntry<>(DbdIndexFields.TABLE, outcomes[1]),
                new MapEntry<>(DbdIndexFields.INDEX, outcomes[2]),
                new MapEntry<>(DbdIndexFields.DBUNIQUE, outcomes[3]),
                new MapEntry<>(DbdIndexFields.OWNER, outcomes[4]),
                new MapEntry<>(DbdIndexFields.TABLESPACE, outcomes[5]),
                new MapEntry<>(DbdIndexFields.DBMSTYPE, outcomes[6]),
                new MapEntry<>(DbdIndexFields.DDL, outcomes[7]),
                new MapEntry<>(DbdIndexFields.DBCOLUMN, outcomes[8])
            )
        );
    }

}
