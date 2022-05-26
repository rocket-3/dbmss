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
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.Joined;
import org.cactoos.text.Newline;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.mapping.fields.DbdDomainConstraintFields;
import ru.fusionsoft.database.mapping.fields.DbdDomainFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldExistence;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldMapped;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.snapshot.objects.generated.ConstraintsOfDomain;
import ru.fusionsoft.database.text.TextOfLinesConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

public class PgDomainCreateSql implements Text {

    private final DbObject<DbdDomainMapping> object;

    public PgDomainCreateSql(final DbObject<DbdDomainMapping> object) {
        this.object = object;
    }

    @Override
    public String asString() {
        return new TextOfLinesConditional(
            new MapEntry<>(
                () -> true,
                () -> new TextOfMessageFormat(
                    "CREATE DOMAIN {0}.{1} AS {2}{3};",
                    () -> this.object.signature().name().parent(),
                    () -> this.object.signature().name().first(),
                    () -> new TextOfObjectField(
                        this.object,
                        DbdDomainFields.TYPE,
                        string -> new TextOf(string)
                    ),
                    () -> new TextOfObjectField(
                        this.object,
                        DbdDomainFields.DEFAULT,
                        string -> new TextOfMessageFormat(" DEFAULT {0}", string),
                        new TextOf("")
                    )
                )
            ),
            new MapEntry<>(
                new ObjectFieldExistence(this.object, DbdDomainFields.CONSTRAINTS),
                new ObjectFieldMapped<Text>(
                    this.object,
                    DbdDomainFields.CONSTRAINTS,
                    node -> new Joined(
                        new Newline(),
                        new Mapped<Text>(
                            con -> new TextOfMessageFormat(
                                "ALTER DOMAIN {0}.{1} ADD CONSTRAINT {2} CHECK {3} {4};",
                                this.object.signature().name().parent(),
                                this.object.signature().name().first(),
                                con.signature().name().first(),
                                new TextOfObjectField(con, DbdDomainConstraintFields.CONDITION),
                                new TextOfObjectField(con, DbdDomainConstraintFields.VALIDATED,
                                    string -> new Ternary<Text>(
                                        () -> Boolean.valueOf(string),
                                        () -> new TextOf(""),
                                        () -> new TextOf("NOT VALID")
                                    ).value()
                                )
                            ),
                            new ConstraintsOfDomain(this.object)
                        )
                    ),
                    () -> new TextOf("")
                )
            ),
            new MapEntry<>(
                new ObjectFieldExistence(this.object, DbdDomainFields.OWNER),
                () -> new TextOfObjectField(
                    this.object,
                    DbdDomainFields.OWNER,
                    string -> new TextOfMessageFormat(
                        "ALTER DOMAIN {0}.{1} OWNER TO {2};",
                        this.object.signature().name().parent(),
                        this.object.signature().name().first(),
                        () -> string
                    )
                )
            )
        ).asString();
    }

}

