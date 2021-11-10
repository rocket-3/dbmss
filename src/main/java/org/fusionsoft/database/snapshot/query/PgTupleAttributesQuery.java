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

import org.fusionsoft.database.mapping.fields.DbdTupleAttributeField;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.query.pg.PgMessageFormatQuery;

public class PgTupleAttributesQuery extends PgMessageFormatQuery<DbdTupleAttributeField> {

    public PgTupleAttributesQuery(final FullObjectName tuple) {
        super(
            () -> String.join(
                "",
                "SELECT \n",
                "    attribute_name   AS {0}, \n",
                "    data_type        AS {1}, \n",
                "    ordinal_position AS {2},\n",
                "    case\n",
                "        when lower(data_type) in (\n",
                "            'integer', 'numeric', 'smallint', \n",
                "            'double precision', 'bigint', 'oid'\n",
                "        ) then 'number' \n",
                "        when lower(data_type) in (\n",
                "            'character varying', 'char', 'character', \n",
                "            'varchar', 'text', 'name'\n",
                "        ) then 'string'\n",
                "        when lower(data_type) in (\n",
                "            'timestamp without time zone', \n",
                "            'timestamp with time zone', 'date'\n",
                "        ) then 'date'\n",
                "        when lower(data_type) in ('boolean') then 'boolean'\n",
                "        when lower(data_type) in ('bytea') then 'binary'\n",
                "        when lower(data_type) in ('array') then 'array'\n",
                "        when lower(data_type) like '%json%' then 'json'\n",
                "        else 'native'\n",
                "    end AS {3}\n",
                "FROM information_schema.attributes a\n",
                "WHERE udt_schema = '", tuple.parent().asString(), "'\n",
                "AND udt_name = '", tuple.first().asString(), "'\n",
                "ORDER BY ordinal_position\n"
            ),
            DbdTupleAttributeField.ATTRIBUTE,
            DbdTupleAttributeField.TYPE,
            DbdTupleAttributeField.ORDER,
            DbdTupleAttributeField.IUTYPE
        );
    }

}
