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
package org.fusionsoft.database.snapshot.objects.dbms.resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import org.cactoos.Func;
import org.cactoos.map.MapEntry;
import org.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdColumnFields;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.database.snapshot.query.QueryOfScalar;
import org.fusionsoft.lib.collection.ListOfResultSet;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlSequenceOfNodes;

/**
 * The type of {@link org.fusionsoft.database.snapshot.DbObject}
 * of {@link DbdTableFields}
 * that can be constructed of {@link Connection}, {@link Query} and {@link ResultSet}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (256 lines)
 * @checkstyle ParameterNumberCheck (256 lines)
 */
public class TableOfResultSet extends SimpleDbObject<DbdTableMapping> {

    /**
     * Instantiates a new Simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdTableFields to be encapsulated.
     * @param columns The Func of FullObjectName -> Query of DbdColumnFields to be encapsulated.
     */
    public TableOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTableFields> query,
        final Func<FullObjectName, Query<DbdColumnFields>> columns
    ) {
        this(
            rset,
            connection,
            query,
            columns,
            new FullObjectName(
                new TextOfResultSet(
                    query.outcomeFor(DbdTableFields.SCHEMA),
                    rset
                ),
                new TextOfResultSet(
                    query.outcomeFor(DbdTableFields.TABLE),
                    rset
                )
            )
        );
    }

    /**
     * Instantiates a new Simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdIndexFields to be encapsulated.
     * @param columns The Func of FullObjectName -> Query of DbdColumnFields to be encapsulated.
     * @param tablename The FullObjectName to be encapsulated.
     */
    private TableOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTableFields> query,
        final Func<FullObjectName, Query<DbdColumnFields>> columns,
        final FullObjectName tablename
    ) {
        this(
            rset,
            connection,
            query,
            new QueryOfScalar<>(() -> columns.apply(tablename)),
            tablename
        );
    }

    /**
     * Instantiates a new Simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdTableFields to be encapsulated.
     * @param columns The Query of DbdColumnFields to be encapsulated.
     * @param tablename The FullObjectName to be encapsulated.
     */
    private TableOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTableFields> query,
        final Query<DbdColumnFields> columns,
        final FullObjectName tablename
    ) {
        super(
            new DbdTableMapping(
                new YamlMappingOfEntries(
                    new MapEntry<>(
                        DbdTableFields.COLUMNS,
                        new YamlSequenceOfNodes(
                            new ListOfResultSet<DbdColumnMapping>(
                                rs -> new DbdColumnMappingOfResultSet(rs, columns),
                                columns,
                                connection
                            )
                        )
                    ),
                    new ScalarEntry(
                        DbdTableFields.OWNER,
                        new TextOfResultSet(
                            query.outcomeFor(DbdTableFields.OWNER),
                            rset
                        )
                    )
                )
            ),
            new SimpleObjectSignature(
                tablename,
                ObjectType.TABLE
            )
        );
    }

}
