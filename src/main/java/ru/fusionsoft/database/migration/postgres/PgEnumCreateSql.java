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
import org.cactoos.map.MapEntry;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdEnumMapping;
import ru.fusionsoft.database.mapping.fields.DbdEnumField;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldExistence;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldMapped;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.text.TextOfConditionsLines;
import ru.fusionsoft.lib.text.TextOfMessageFormat;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfYamlSequence;

/**
 * The sql Text for Postgres DBMS to create any enum UDT of given enum UDT {@link DbObject}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines).
 */
public class PgEnumCreateSql implements Text {

    /**
     * The DbObject of {@link DbdEnumMapping}.
     */
    private final DbObject<DbdEnumMapping> object;

    /**
     * Instantiates a new Pg enum create sql.
     * @param object The DbObject of {@link DbdEnumMapping}.
     */
    public PgEnumCreateSql(final DbObject<DbdEnumMapping> object) {
        this.object = object;
    }

    @Override
    public final String asString() {
        return new TextOfConditionsLines(
            new MapEntry<>(
                () -> true,
                () -> new TextOfMessageFormat(
                    "CREATE TYPE {0}.{1} AS ENUM ({2});",
                    () -> this.object.signature().name().parent(),
                    () -> this.object.signature().name().first(),
                    new ObjectFieldMapped<>(
                        this.object,
                        DbdEnumField.ELEMENTS,
                        elements -> new Joined(
                            new TextOf(","),
                            new IterableOfYamlSequence<>(
                                elements,
                                element -> new TextOfMessageFormat(
                                    "''{0}''",
                                    element.asScalar().value()
                                )
                            )
                        ),
                        () -> new TextOf("")
                    )
                )
            ),
            new MapEntry<>(
                new ObjectFieldExistence(
                    this.object,
                    DbdEnumField.OWNER
                ),
                () -> new TextOfMessageFormat(
                    "ALTER TYPE {0}.{1} OWNER TO \"{2}\";",
                    () -> this.object.signature().name().parent(),
                    () -> this.object.signature().name().first(),
                    () -> new TextOfObjectField(
                        this.object,
                        DbdEnumField.OWNER
                    )
                )
            )
        ).asString();
    }

}
