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
import ru.fusionsoft.database.mapping.dbd.DbdViewMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

public class PgViewDropSql implements Text {

    private final DbObject<DbdViewMapping> object;

    public PgViewDropSql(final DbObject<DbdViewMapping> object) {
        this.object = object;
    }

    @Override
    public String asString() {
        return new TextOfMessageFormat(
            "DROP VIEW {0}.{1};",
            () -> this.object.signature().name().parent(),
            () -> this.object.signature().name().first()
        ).asString();
    }

}
