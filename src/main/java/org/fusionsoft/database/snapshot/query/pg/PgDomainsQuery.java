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

import org.fusionsoft.database.mapping.fields.DbdDomainFields;

public class PgDomainsQuery extends PgMessageFormatQuery<DbdDomainFields> {

    public PgDomainsQuery() {
        super(
            "SELECT \n"
            + "    n.nspname AS {0},\n"
            + "    t.typname AS {1}, \n"
            + "    dom.data_type AS {2}, \n"
            + "    dom.domain_default AS {3},\n"
            + "    r.rolname AS {4},\n"
            + "    pg_catalog.obj_description(t.oid, 'pg_type') AS {5}\n"
            + "FROM        pg_type t \n"
            + "LEFT JOIN   pg_catalog.pg_namespace n ON n.oid = t.typnamespace \n"
            + "RIGHT JOIN  information_schema.domains dom \n"
            + "ON dom.domain_name = t.typname \n"
            + "AND dom.domain_schema = n.nspname\n"
            + "LEFT JOIN   pg_roles r \n"
            + "ON r.oid = t.typowner\n"
            + "WHERE (\n"
            + "    t.typrelid = 0 \n"
            + "    OR (\n"
            + "        SELECT c.relkind = 'c'\n"
            + "        FROM pg_catalog.pg_class c \n"
            + "        WHERE c.oid = t.typrelid\n"
            + "    )\n"
            + ") \n"
            + "AND NOT EXISTS(\n"
            + "    SELECT 1 \n"
            + "    FROM pg_catalog.pg_type el \n"
            + "    WHERE el.oid = t.typelem \n"
            + "    AND el.typarray = t.oid\n"
            + ")\n"
            + "AND n.nspname NOT IN ('pg_catalog', 'information_schema')",
            DbdDomainFields.SCHEMA,
            DbdDomainFields.DOMAIN,
            DbdDomainFields.TYPE,
            DbdDomainFields.DEFAULT,
            DbdDomainFields.ONWER,
            DbdDomainFields.DESCRIPTION
        );
    }

}
