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
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectSignatureOfScalar;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

public class PgIndexDropMigration implements Migration {

    private final ObjectSignature signature;

    private final Text ddl;

    private PgIndexDropMigration(final ObjectSignature signature, final Text ddl) {
        this.signature = signature;
        this.ddl = ddl;
    }

    public PgIndexDropMigration(final DbObject<DbdIndexMapping> index) {
        this(
            new ObjectSignatureOfScalar(index::signature),
            new TextOfScalar(
                new Ternary<String>(
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
                    ).asString(),
                    () -> new TextOfMessageFormat(
                        "ALTER TABLE {0}.{1} ADD CONSTRAINT {2} {3};",
                        () -> index.signature().name().parent().parent(),
                        () -> index.signature().name().parent().first(),
                        () -> index.signature().name().first(),
                        () -> new TextOfObjectField(index, DbdConstraintFields.DDL)
                    ).asString()
                )
            )
        );
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Creating index {1} of table {0}",
            () -> signature.name().parent(),
            () -> signature.name().first()
        );
    }

    @Override
    public final Text sql() {
        return this.ddl;
    }

}
