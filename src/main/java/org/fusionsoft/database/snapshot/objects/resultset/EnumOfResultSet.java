/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database.snapshot.objects.resultset;

import java.sql.ResultSet;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.dbd.DbdEnumMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import org.fusionsoft.database.mapping.fields.DbdEnumField;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

public class EnumOfResultSet extends ObjectOfEntries<DbdEnumMapping> {

    public EnumOfResultSet(final ResultSet rset, final Query<DbdEnumField> query) {
        super(
            ObjectType.UDT_ENUM,
            DbdEnumMapping::new,
            new IterableOf<Text>(
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
