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
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdFunctionMapping;
import ru.fusionsoft.database.mapping.fields.DbdFunctionFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.text.PgProcedureArgs;
import ru.fusionsoft.lib.text.TextOfMessageFormat;
import ru.fusionsoft.lib.yaml.artefacts.MaybeEmptyTextOfYamlMapping;

public class PgFunctionDropSql implements Text {

    private final DbObject<DbdFunctionMapping> object;

    public PgFunctionDropSql(final DbObject<DbdFunctionMapping> object) {
        this.object = object;
    }

    @Override
    public String asString() {
        return new TextOfMessageFormat(
            "DROP {3} {0}.{1}({2});",
            () -> this.object.signature().name().parent(),
            () -> this.object.signature().name().first(),
            () -> new PgProcedureArgs(this.object),
            () -> new Ternary<Text>(
                () -> Boolean.parseBoolean(
                    new MaybeEmptyTextOfYamlMapping(
                        this.object.asYaml(),
                        DbdFunctionFields.AGGREGATE
                    ).asString()
                ),
                () -> new TextOf("AGGREGATE"),
                () -> new TextOf("FUNCTION")
            ).value()
        ).asString();
    }

}
