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
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to drop any constraint of given constraint {@link DbObject}.
 * @since 0.1
 */
public class PgConstraintDropSql implements Text {

    /**
     * The DbObject of {@link DbdConstraintMapping}.
     */
    private final DbObject<DbdConstraintMapping> object;

    /**
     * Instantiates a new Pg constraint drop sql.
     * @param object The DbObject of {@link DbdConstraintMapping}.
     */
    public PgConstraintDropSql(final DbObject<DbdConstraintMapping> object) {
        this.object = object;
    }

    @Override
    public final String asString() throws Exception {
        return new Ternary<Text>(
            () -> new TextOfObjectField(
                this.object,
                DbdConstraintFields.TYPE
            ).asString().equals(
                ConstraintTypeValues.NOT_NULL.asString()
            ),
            () -> new TextOfMessageFormat(
                "ALTER TABLE {0}.{1} ALTER COLUMN {2} DROP NOT NULL;",
                () -> this.object.signature().name().parent().parent(),
                () -> this.object.signature().name().parent().first(),
                () -> new TextOfObjectField(this.object, DbdConstraintFields.SRC_PK_COL)
            ).asString(),
            () -> new TextOfMessageFormat(
                "ALTER TABLE {0}.{1} DROP CONSTRAINT {2};",
                () -> this.object.signature().name().parent().parent().first(),
                () -> this.object.signature().name().parent().first(),
                () -> this.object.signature().name().first()
            ).asString()
        ).value().asString();
    }

}

