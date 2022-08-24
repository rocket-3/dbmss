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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapEntry;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectFieldExistence;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfText;
import ru.fusionsoft.database.text.TextOfConditionsLines;
import ru.fusionsoft.database.text.TextOfConditionsSpaced;
import ru.fusionsoft.lib.text.TextOfMessageFormat;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * The sql Text for Postgres DBMS to create any table of given table {@link DbObject}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines).
 */
@SuppressWarnings("PMD.ExcessiveMethodLength")
public class PgTableCreateSql implements Text {

    /**
     * The DbObject of table.
     */
    private final DbObject<? extends YamlNode> object;

    /**
     * Instantiates a new Pg table create sql.
     * @param object The DbObject of table.
     */
    public PgTableCreateSql(final DbObject<? extends YamlNode> object) {
        this.object = object;
    }

    @Override
    public final String asString() {
        return new TextOfConditionsLines(
            new MapEntry<>(
                () -> true,
                () -> new TextOfConditionsSpaced(
                    new MapEntry<>(
                        () -> true,
                        () -> new TextOfMessageFormat(
                            "CREATE TABLE {0}.{1}({2})",
                            () -> this.object.signature().name().parent(),
                            () -> this.object.signature().name().first(),
                            () -> new Joined(
                                new TextOf(", "),
                                new Mapped<Text>(
                                    column -> new TextOfConditionsSpaced(
                                        new MapEntry<>(
                                            () -> true,
                                            () -> new TextOfMessageFormat(
                                                "{0} {1}",
                                                () -> new TextOfYamlMappingKeyValue(
                                                    column,
                                                    DbdColumnFields.DBNAME
                                                ),
                                                () -> new TextOfYamlMappingKeyValue(
                                                    column,
                                                    DbdColumnFields.DBMSTYPE
                                                )
                                            )
                                        ),
                                        new MapEntry<>(
                                            new ObjectFieldExistence(
                                                column::asMapping,
                                                DbdColumnFields.DEFAULT
                                            ),
                                            () -> new TextOfMessageFormat(
                                                "DEFAULT {0}",
                                                () -> new TextOfYamlMappingKeyValue(
                                                    column,
                                                    DbdColumnFields.DEFAULT
                                                )
                                            )
                                        )
                                    ),
                                    new DbdColumnsOfTable(
                                        this.object
                                    )
                                )
                            )
                        )
                    ),
                    new MapEntry<>(
                        new ObjectFieldExistence(this.object, DbdTableFields.PARTKEYDEFINITION),
                        () -> new TextOfMessageFormat(
                            "PARTITION BY {0}",
                            new TextOfObjectField(
                                this.object,
                                DbdTableFields.PARTKEYDEFINITION
                            )
                        )
                    ),
                    new MapEntry<>(
                        new ObjectFieldExistence(this.object, DbdTableFields.TABLESPACE),
                        () -> new TextOfMessageFormat(
                            "TABLESPACE \"{0}\"",
                            new TextOfObjectField(
                                this.object,
                                DbdTableFields.TABLESPACE
                            )
                        )
                    ),
                    new MapEntry<>(
                        () -> true,
                        () -> new TextOf(";")
                    )
                )
            ),
            new MapEntry<>(
                new ObjectFieldExistence(this.object, DbdTableFields.OWNER),
                () -> new TextOfMessageFormat(
                    "ALTER TABLE {0}.{1} OWNER TO {2};",
                    () -> this.object.signature().name().parent(),
                    () -> this.object.signature().name().first(),
                    () -> new TextOfObjectField(this.object, DbdTableFields.OWNER)
                )
            ),
            new MapEntry<>(
                new ObjectFieldExistence(this.object, DbdTableFields.PARENT),
                () -> new TextOfMessageFormat(
                    "ALTER TABLE {0}.{1} ATTACH PARTITION {0}.{2} {3};",
                    () -> this.object.signature().name().parent(),
                    () -> new SimpleObjectNameOfText(
                        new TextOfObjectField(
                            this.object,
                            DbdTableFields.PARENT
                        )
                    ).first(),
                    () -> this.object.signature().name().first(),
                    () -> new TextOfObjectField(this.object, DbdTableFields.PARTKEYRANGE)
                )
            )
        ).asString();
    }

}
