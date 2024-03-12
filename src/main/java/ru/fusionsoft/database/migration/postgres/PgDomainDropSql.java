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
package ru.fusionsoft.database.migration.postgres;

import org.cactoos.Text;
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to drop any domain UDT of given domain UDT {@link DbObject}.
 * @since 0.1
 */
public class PgDomainDropSql implements Text {

    /**
     * The DbObject of {@link DbdDomainMapping}.
     */
    private final DbObject<DbdDomainMapping> object;

    /**
     * Instantiates a new Pg domain drop sql.
     * @param object The The DbObject of {@link DbdDomainMapping}.
     */
    public PgDomainDropSql(final DbObject<DbdDomainMapping> object) {
        this.object = object;
    }

    @Override
    public final String asString() {
        return new TextOfMessageFormat(
            "DROP DOMAIN {0}.{1} RESTRICT;",
            this.object.signature().name().parent(),
            this.object.signature().name().first()
        ).asString();
    }

}
