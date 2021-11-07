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
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.dbd.DbdIndexMapping;
import org.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdIndexFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

/**
 * The type of {@link org.fusionsoft.database.snapshot.DbObject}
 *  of {@link DbdIndexMapping}, constructed of {@link ResultSet} and {@link Query}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (128 lines)
 */
public class IndexOfResultSet extends SimpleDbObject<DbdIndexMapping> {

    /**
     * Instantiates a new Simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdIndexFields to be encapsulated.
     */
    public IndexOfResultSet(final ResultSet rset, final Query<DbdIndexFields> query) {
        super(
            new DbdIndexMapping(
                new YamlMappingOfEntries(
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
                    new MultilineScalarEntry(
                        DbdIndexFields.DDL,
                        new TextOfResultSet(
                            query.outcomeFor(DbdIndexFields.DDL),
                            rset
                        )
                    )
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(
                    new TextOfResultSet(
                        query.outcomeFor(DbdIndexFields.SCHEMA),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdIndexFields.TABLE),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdIndexFields.INDEX),
                        rset
                    )
                ),
                ObjectType.INDEX
            )
        );
    }

}
