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

import ru.fusionsoft.database.mapping.fields.DbdSchemaFields;

/**
 * The only {@link PgMessageFormatQuery} of {@link DbdSchemaFields}.
 * @since 0.1
 */
public class PgSchemasQuery extends PgMessageFormatQuery<DbdSchemaFields> {

    /**
     * Instantiates a new Pg schemas query.
     */
    public PgSchemasQuery() {
        super(
            String.join(
                "",
                "select nspname AS {0}, usename AS {1}\n",
                " from pg_namespace,pg_user\n",
                " where nspname!='pg_toast' and nspname!='pg_temp_1'\n",
                " and nspname!='pg_toast_temp_1' and nspname!='pg_catalog'\n",
                " and nspname!='information_schema' and nspname!='pgagent'\n",
                " and nspname!='pg_temp_3' and nspname!='pg_toast_temp_3'\n",
                " and usesysid = nspowner\n"
            ),
            DbdSchemaFields.SCHEMA,
            DbdSchemaFields.OWNER
        );
    }

}
