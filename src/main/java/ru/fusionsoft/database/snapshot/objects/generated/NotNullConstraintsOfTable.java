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
package ru.fusionsoft.database.snapshot.objects.generated;

import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.database.text.DbdColumnIdentity;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;
import ru.fusionsoft.lib.text.TextOfMessageFormat;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * Constraints {@link DbObject}'s extracted of {@link DbdDomainMapping} {@link DbObject}.
 * @since 0.1
 */
public class NotNullConstraintsOfTable
    extends IterableOfScalarSticky<DbObject<DbdConstraintMapping>> {

    /**
     * Instantiates a new Not null constraints of table.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public NotNullConstraintsOfTable(final DbObject<DbdTableMapping> table) {
        super(
            () -> {
                return new Mapped<>(
                    column -> {
                        final DbdColumnIdentity colname = new DbdColumnIdentity(column);
                        final TextOfMessageFormat conname = new TextOfMessageFormat(
                            new TextOf("{0}_{1}_{2}_is_not_null"),
                            table.signature().name().parent(),
                            table.signature().name().first(),
                            colname
                        );
                        return new SimpleDbObject<>(
                            new DbdConstraintMapping(
                                new YamlMappingOfEntries(
                                    new ScalarEntry(
                                        DbdConstraintFields.SCHEMA,
                                        table.signature().name().parent()
                                    ),
                                    new ScalarEntry(
                                        DbdConstraintFields.TABLE,
                                        table.signature().name().first()
                                    ),
                                    new ScalarEntry(
                                        DbdConstraintFields.CONSTRAINT,
                                        conname
                                    ),
                                    new ScalarEntry(
                                        DbdConstraintFields.TYPE,
                                        ConstraintTypeValues.NOT_NULL
                                    ),
                                    new ScalarEntry(
                                        DbdConstraintFields.SRC_PK_COL,
                                        colname
                                    )
                                )
                            ),
                            new SimpleObjectSignature(
                                new SimpleObjectName(
                                    table.signature().name(),
                                    conname
                                ),
                                new ObjectTypeConstraint()
                            )
                        );
                    },
                    new Filtered<DbdColumnMapping>(
                        mapping -> !Boolean.parseBoolean(
                            new TextOfYamlMappingKeyValue(
                                mapping,
                                DbdColumnFields.DBNULLABLE
                            ).asString()
                        ),
                        new DbdColumnsOfTable(table)
                    )
                );
            }
        );
    }

}
