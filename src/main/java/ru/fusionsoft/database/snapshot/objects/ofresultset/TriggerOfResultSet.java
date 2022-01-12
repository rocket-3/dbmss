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
import ru.fusionsoft.database.mapping.dbd.DbdTriggerMapping;
import ru.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdTriggerFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;

/**
 * The type of {@link DbdTriggerMapping},
 *  can be constructed of {@link ResultSet} and {@link Query} of {@link DbdTriggerFields}.
 * @since 0.1
 */
public class TriggerOfResultSet extends SimpleDbObjectOfEntries<DbdTriggerMapping> {

    /**
     * Instantiates a new Trigger of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of {@link DbdTriggerFields} to be encapsulated.
     */
    public TriggerOfResultSet(final ResultSet rset, final Query<DbdTriggerFields> query) {
        super(
            new ObjectTypeTrigger(),
            new SimpleObjectNameOfResultSet(
                rset,
                query,
                DbdTriggerFields.SCHEMA,
                DbdTriggerFields.TABLE,
                DbdTriggerFields.TRIGGER
            ),
            new MultilineScalarEntry(
                DbdTriggerFields.DDL,
                new TextOfResultSet(
                    DbdTriggerFields.DDL,
                    rset
                )
            )
        );
    }

}
