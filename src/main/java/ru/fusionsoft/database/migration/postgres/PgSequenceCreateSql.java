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
import ru.fusionsoft.database.mapping.dbd.DbdSequenceMapping;
import ru.fusionsoft.database.mapping.fields.DbdSequenceFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to create any sequence of given sequence {@link DbObject}.
 * @since 0.1
 */
public class PgSequenceCreateSql implements Text {

    /**
     * The DbObject of {@link DbdSequenceMapping}.
     */
    private final DbObject<DbdSequenceMapping> object;

    /**
     * Instantiates a new Pg sequence create sql.
     * @param object The DbObject of {@link DbdSequenceMapping}.
     */
    public PgSequenceCreateSql(final DbObject<DbdSequenceMapping> object) {
        this.object = object;
    }

    @Override
    public final String asString() {
        return new TextOfMessageFormat(
            "CREATE SEQUENCE \"{0}\".\"{1}\" {2}{3}{4}{5}{6}{7};",
            () -> this.object.signature().name().parent(),
            () -> this.object.signature().name().first(),
            () -> new TextOfObjectField(
                this.object,
                DbdSequenceFields.CYCLE,
                string -> new Ternary<Text>(
                    Boolean.parseBoolean(string),
                    () -> "cycle",
                    () -> "no cycle"
                ).value(),
                () -> ""
            ),
            () -> new TextOfObjectField(
                this.object,
                DbdSequenceFields.START,
                string -> new TextOfMessageFormat(" start {0}", string),
                () -> ""
            ),
            () -> new TextOfObjectField(
                this.object,
                DbdSequenceFields.INCREMENT,
                string -> new TextOfMessageFormat(" increment {0}", string),
                () -> ""
            ),
            () -> new TextOfObjectField(
                this.object,
                DbdSequenceFields.MIN,
                string -> new TextOfMessageFormat(" minvalue {0}", string),
                () -> ""
            ),
            () -> new TextOfObjectField(
                this.object,
                DbdSequenceFields.MAX,
                string -> new TextOfMessageFormat(" maxvalue {0}", string),
                () -> ""
            ),
            () -> new TextOfObjectField(
                this.object,
                DbdSequenceFields.OWNER,
                string -> new TextOfMessageFormat(
                    ";\nALTER SEQUENCE {0}.{1} OWNER TO {2}",
                    this.object.signature().name().parent().asString(),
                    this.object.signature().name().first().asString(),
                    string
                ),
                () -> ""
            )
        ).asString();
    }

}
