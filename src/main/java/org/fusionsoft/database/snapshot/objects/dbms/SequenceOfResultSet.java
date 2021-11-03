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
import org.fusionsoft.database.mapping.dbd.DbdSequenceMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdSequenceFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

public class SequenceOfResultSet extends SimpleDbObject<DbdSequenceMapping> {

    public SequenceOfResultSet(
        final ResultSet rset,
        final Query<DbdSequenceFields> query
    ) {
        super(
            new DbdSequenceMapping(
                new YamlMappingOfEntries(
                    new ScalarEntry(
                        DbdSequenceFields.DUMMY,
                        new TextOfResultSet(
                            query.outcomeFor(DbdSequenceFields.DUMMY),
                            rset
                        )
                    )
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(""),
                ObjectType.SEQUENCE
            )
        );
    }

}
