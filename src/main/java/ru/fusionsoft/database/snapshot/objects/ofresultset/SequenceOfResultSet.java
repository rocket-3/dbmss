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
import ru.fusionsoft.database.mapping.dbd.DbdSequenceMapping;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdSequenceFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSequence;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;

/**
 * The type of {@link SimpleDbObject} of {@link DbdSequenceMapping}
 *  that can be constructed of {@link ResultSet} and {@link Query} of {@link DbdSequenceFields}.
 * @since 0.1
 */
public class SequenceOfResultSet extends SimpleDbObjectOfEntries<DbdSequenceMapping> {

    /**
     * Instantiates a new Sequence of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdSequenceFields to be encapsulated.
     */
    public SequenceOfResultSet(
        final ResultSet rset,
        final Query<DbdSequenceFields> query
    ) {
        super(
            new ObjectTypeSequence(),
            new SimpleObjectNameOfResultSet(
                rset,
                query,
                DbdSequenceFields.SCHEMA,
                DbdSequenceFields.SEQUENCE
            ),
            new ScalarEntry(
                DbdSequenceFields.OWNER,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.OWNER),
                    rset
                )
            ),
            new ScalarEntry(
                DbdSequenceFields.START,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.START),
                    rset
                )
            ),
            new ScalarEntry(
                DbdSequenceFields.INCREMENT,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.INCREMENT),
                    rset
                )
            ),
            new ScalarEntry(
                DbdSequenceFields.MIN,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.MIN),
                    rset
                )
            ),
            new ScalarEntry(
                DbdSequenceFields.MAX,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.MAX),
                    rset
                )
            ),
            new ScalarEntry(
                DbdSequenceFields.CYCLE,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.CYCLE),
                    rset
                )
            ),
            new ScalarEntry(
                DbdSequenceFields.DEP_TABLE,
                new TextOfResultSet(
                    query.outcomeFor(DbdSequenceFields.DEP_TABLE),
                    rset
                )
            )
        );
    }

}
