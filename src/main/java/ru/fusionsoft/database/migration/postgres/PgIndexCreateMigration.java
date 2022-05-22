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
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

public class PgIndexCreateMigration implements Migration {

    private final DbObject<DbdIndexMapping> index;

    public PgIndexCreateMigration(final DbObject<DbdIndexMapping> index) {
        this.index = index;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Creating index {1} of table {0}",
            () -> this.index.signature().name().parent(),
            () -> this.index.signature().name().first()
        );
    }

    @Override
    public final Text sql() {
        return new Unchecked<>(
            new Ternary<TextOfMessageFormat>(
                () -> new TextOfObjectField(
                    index,
                    DbdConstraintFields.TYPE
                ).asString().equals(
                    ConstraintTypeValues.NOT_NULL.asString()
                ),
                () -> new TextOfMessageFormat(
                    "ALTER TABLE {0}.{1} ALTER COLUMN {2} SET NOT NULL;",
                    () -> index.signature().name().parent().parent(),
                    () -> index.signature().name().parent().first(),
                    () -> new TextOfObjectField(index, DbdConstraintFields.SRC_PK_COL)
                ),
                () -> new TextOfMessageFormat(
                    "ALTER TABLE {0}.{1} ADD CONSTRAINT {2} {3};",
                    () -> index.signature().name().parent().parent(),
                    () -> index.signature().name().parent().first(),
                    () -> index.signature().name().first(),
                    () -> new TextOfObjectField(index, DbdConstraintFields.DDL)
                )
            )
        ).value();
    }

}
