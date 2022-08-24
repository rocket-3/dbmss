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
import ru.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to drop any index of given index {@link DbObject}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines).
 */
public class PgIndexDropSql implements Text {

    /**
     * The DbObject of {@link DbdIndexMapping}.
     */
    private final DbObject<DbdIndexMapping> index;

    /**
     * Instantiates a new Pg index drop sql.
     * @param index The DbObject of {@link DbdIndexMapping}.
     */
    public PgIndexDropSql(final DbObject<DbdIndexMapping> index) {
        this.index = index;
    }

    @Override
    public final String asString() {
        return new TextOfMessageFormat(
            "DROP INDEX {0}.{1};",
            () -> this.index.signature().name().parent().parent().first(),
            () -> this.index.signature().name().first()
        ).asString();
    }

}
