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
import ru.fusionsoft.database.mapping.dbd.DbdTupleMapping;
import ru.fusionsoft.database.mapping.fields.DbdTupleAttributeField;
import ru.fusionsoft.database.mapping.fields.DbdTupleField;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldExistence;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldMapped;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.text.TextOfLinesConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;
import ru.fusionsoft.lib.yaml.artefacts.IterableOfYamlSequence;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

public class PgTupleCreateSql implements Text {

    private final DbObject<DbdTupleMapping> object;

    public PgTupleCreateSql(final DbObject<DbdTupleMapping> object) {
        this.object = object;
    }

    @Override
    public String asString() {
        return new TextOfLinesConditional(
            new MapEntry<>(
                () -> true,
                () -> new TextOfMessageFormat(
                    "CREATE TYPE {0}.{1} AS ({2});",
                    () -> this.object.signature().name().parent(),
                    () -> this.object.signature().name().first(),
                    new ObjectFieldMapped<>(
                        this.object,
                        DbdTupleField.ATTRIBUTES,
                        attributes -> new Joined(
                            new TextOf(","),
                            new IterableOfYamlSequence<>(
                                attributes,
                                attribute -> new TextOfMessageFormat(
                                    "{0} {1}",
                                    new TextOfYamlMappingKeyValue(
                                        attribute.asMapping(),
                                        DbdTupleAttributeField.ATTRIBUTE
                                    ),
                                    new TextOfYamlMappingKeyValue(
                                        attribute.asMapping(),
                                        DbdTupleAttributeField.TYPE
                                    )
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
                    DbdTupleField.OWNER
                ),
                () -> new TextOfMessageFormat(
                    "ALTER TYPE {0}.{1} OWNER TO \"{2}\";",
                    () -> this.object.signature().name().parent(),
                    () -> this.object.signature().name().first(),
                    () -> new TextOfObjectField(
                        this.object,
                        DbdTupleField.OWNER
                    )
                )
            )
        ).asString();
    }

}
