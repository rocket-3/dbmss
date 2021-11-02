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
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdConstraintFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

public class ConstraintOfResultSet extends SimpleDbObject<DbdConstraintMapping> {

    /**
     * Instantiates a new simple db object.
     */
    public ConstraintOfResultSet(final ResultSet rset, final Query<DbdConstraintFields> query) {
        super(
            new DbdConstraintMapping(
                new YamlMappingOfEntries(
                    new MapEntry<>(
                        DbdConstraintFields.SRC_PK_COL,
                        new YamlScalarSequenceOfResultSet(
                            query.outcomeFor(DbdConstraintFields.SRC_PK_COL),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdConstraintFields.SRC_FK_COL,
                        new TextOfResultSet(
                            query.outcomeFor(DbdConstraintFields.SRC_FK_COL),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdConstraintFields.TGT_SCHEMA,
                        new TextOfResultSet(
                            query.outcomeFor(DbdConstraintFields.TGT_SCHEMA),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdConstraintFields.TGT_TABLE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdConstraintFields.TGT_TABLE),
                            rset
                        )
                    ),
                    new MapEntry<>(
                        DbdConstraintFields.TGT_COL,
                        new YamlScalarSequenceOfResultSet(
                            query.outcomeFor(DbdConstraintFields.TGT_COL),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdConstraintFields.TYPE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdConstraintFields.TYPE),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdConstraintFields.ON_DELETE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdConstraintFields.ON_DELETE),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdConstraintFields.ON_UPDATE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdConstraintFields.ON_UPDATE),
                            rset
                        )
                    )
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(
                    new TextOfResultSet(
                        query.outcomeFor(DbdConstraintFields.SCHEMA),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdConstraintFields.TABLE),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdConstraintFields.CONSTRAINT),
                        rset
                    )
                ),
                ObjectType.INDEX
            )
        );
    }

}
