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
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import org.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdConstraintFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

/**
 * The type of {@link SimpleDbObject} that can be constructed
 *  of {@link ResultSet} and {@link Query} of {@link DbdConstraintFields}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ConstraintOfResultSet extends SimpleDbObject<DbdConstraintMapping> {

    /**
     * Instantiates a new Constraint of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdConstraintFields to be encapsulated.
     */
    public ConstraintOfResultSet(
        final ResultSet rset,
        final Query<DbdConstraintFields> query
    ) {
        this(
            rset,
            query,
            new TextOfResultSet(
                query.outcomeFor(DbdConstraintFields.TYPE),
                rset
            )
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdConstraintFields to be encapsulated.
     * @param type The Text of constraint type to be encapsulated.
     */
    private ConstraintOfResultSet(
        final ResultSet rset,
        final Query<DbdConstraintFields> query,
        final Text type
    ) {
        super(
            new DbdConstraintMapping(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
                        new MapEntry<>(
                            new TextOfScalar(
                                () -> new MapOf<Boolean, Text>(
                                    new MapEntry<>(true, DbdConstraintFields.SRC_FK_COL),
                                    new MapEntry<>(false, DbdConstraintFields.SRC_PK_COL)
                                ).get(type.asString().equals("FK")).asString()
                            ),
                            new YamlScalarSequenceOfResultSet(
                                query.outcomeFor(DbdConstraintFields.SRC_PK_COL),
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
                            type
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
                        ),
                        new MultilineScalarEntry(
                            DbdConstraintFields.DDL,
                            new TextOfResultSet(
                                query.outcomeFor(DbdConstraintFields.DDL),
                                rset
                            )
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
                ObjectType.CONSTRAINT
            )
        );
    }

}
