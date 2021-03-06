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
package ru.fusionsoft.database.snapshot.objects.ofresultset;

import java.sql.ResultSet;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.dbd.DbdEnumMapping;
import ru.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import ru.fusionsoft.database.mapping.fields.DbdEnumField;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeEnum;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;
import ru.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

/**
 * The {@link SimpleDbObjectOfEntries}, can be constructed from
 *  {@link Query} of {@link DbdEnumField} and {@link ResultSet}.
 * @since 0.1
 */
public class EnumOfResultSet extends SimpleDbObjectOfEntries<DbdEnumMapping> {

    /**
     * Instantiates a new Enum of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdEnumField to be encapsulated.
     */
    public EnumOfResultSet(final ResultSet rset, final Query<DbdEnumField> query) {
        super(
            new ObjectTypeEnum(),
            new IterableOf<>(
                new TextOfResultSet(DbdEnumField.SCHEMA, rset, query),
                new TextOfResultSet(DbdEnumField.ENUM, rset, query)
            ),
            new IterableOf<>(
                new ScalarEntryOfResultSet(
                    DbdEnumField.OWNER,
                    rset,
                    query
                ),
                new ScalarEntryOfResultSet(
                    DbdEnumField.DESCRIPTION,
                    rset,
                    query
                ),
                new MapEntry<>(
                    DbdEnumField.ELEMENTS,
                    new YamlScalarSequenceOfResultSet(
                        DbdEnumField.ELEMENTS,
                        query,
                        rset
                    )
                )
            )
        );
    }

}
