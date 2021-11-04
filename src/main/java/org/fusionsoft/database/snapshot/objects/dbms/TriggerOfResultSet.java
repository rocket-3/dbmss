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
package org.fusionsoft.database.snapshot.objects.dbms;

import java.sql.ResultSet;
import org.fusionsoft.database.mapping.dbd.DbdTriggerMapping;
import org.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdTriggerFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link DbdTriggerMapping},
 *  can be constructed of {@link ResultSet} and {@link Query} of {@link DbdTriggerFields}.
 * @since 0.1
 */
public class TriggerOfResultSet extends SimpleDbObject<DbdTriggerMapping> {

    /**
     * Instantiates a new Trigger of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of {@link DbdTriggerFields} to be encapsulated.
     */
    public TriggerOfResultSet(final ResultSet rset, final Query<DbdTriggerFields> query) {
        super(
            new DbdTriggerMapping(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
                        new MultilineScalarEntry(
                            DbdTriggerFields.DDL,
                            new TextOfResultSet(
                                DbdTriggerFields.DDL,
                                rset
                            )
                        )
                    )
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(
                    new TextOfResultSet(
                        query.outcomeFor(DbdTriggerFields.SCHEMA),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdTriggerFields.TABLE),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdTriggerFields.TRIGGER),
                        rset
                    )
                ),
                ObjectType.TRIGGER
            )
        );
    }

}
