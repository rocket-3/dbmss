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
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import ru.fusionsoft.database.mapping.entries.MultilineSqlScalarEntry;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdIndexFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;
import ru.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

/**
 * The type of {@link DbObject}
 *  of {@link DbdIndexMapping}, constructed of {@link ResultSet} and {@link Query}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (128 lines)
 */
public class IndexOfResultSet extends SimpleDbObjectOfEntries<DbdIndexMapping> {

    /**
     * Instantiates a new Simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdIndexFields to be encapsulated.
     */
    public IndexOfResultSet(final ResultSet rset, final Query<DbdIndexFields> query) {
        super(
            new ObjectTypeIndex(),
            new SimpleObjectNameOfResultSet(
                rset,
                query,
                DbdIndexFields.SCHEMA,
                DbdIndexFields.TABLE,
                DbdIndexFields.INDEX
            ),
            new MapEntry<>(
                DbdIndexFields.DBCOLUMN,
                new YamlScalarSequenceOfResultSet(
                    query.outcomeFor(DbdIndexFields.DBCOLUMN),
                    rset
                )
            ),
            new ScalarEntry(
                DbdIndexFields.DBMSTYPE,
                new TextOfResultSet(
                    query.outcomeFor(DbdIndexFields.DBMSTYPE),
                    rset
                )
            ),
            new ScalarEntry(
                DbdIndexFields.DBUNIQUE,
                new TextOfResultSet(
                    query.outcomeFor(DbdIndexFields.DBUNIQUE),
                    rset
                )
            ),
            new ScalarEntry(
                DbdIndexFields.OWNER,
                new TextOfResultSet(
                    query.outcomeFor(DbdIndexFields.OWNER),
                    rset
                )
            ),
            new MultilineSqlScalarEntry(
                DbdIndexFields.DDL,
                new TextOfResultSet(
                    query.outcomeFor(DbdIndexFields.DDL),
                    rset
                )
            )
        );
    }

}
