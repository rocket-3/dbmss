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
package ru.fusionsoft.database.migration.typecasts.sql;

import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * SQL code for postgres to check if possible to cast one type to another.
 * @since 0.1
 */
public class PgTypecastCheckSql extends TextEnvelope {

    /**
     * Instantiates a new Pg typecast check sql.
     * @param source From type text name.
     * @param target To type text name.
     */
    public PgTypecastCheckSql(final Text source, final Text target) {
        super(
            new TextOfMessageFormat(
                String.join(
                    "\n",
                    "DO $$ DECLARE type_from {0};",
                    "BEGIN PERFORM CAST ( type_from AS {1} ); END $$;"
                ),
                source,
                target
            )
        );
    }

}
