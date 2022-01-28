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

import java.sql.Connection;
import java.sql.ResultSet;
import org.cactoos.Func;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.dbd.DbdColumnsMappingOfEntries;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.dbd.ofresultset.DbdColumnMappingOfResultSet;
import ru.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.database.snapshot.query.QueryOfScalar;
import ru.fusionsoft.lib.collection.ListOfResultSet;
import ru.fusionsoft.lib.text.TextOfResultSet;
import ru.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

/**
 * The type of {@link DbObject}
 * of {@link DbdTableFields}
 * that can be constructed of {@link Connection}, {@link Query} and {@link ResultSet}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (256 lines)
 * @checkstyle ParameterNumberCheck (256 lines)
 */
public class TableOfResultSet extends SimpleDbObjectOfEntries<DbdTableMapping> {

    /**
     * Instantiates a new Simple db object.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdTableFields to be encapsulated.
     * @param columns The Func of {@link ObjectName} -> Query of DbdColumnFields to be encapsulated.
     */
    public TableOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTableFields> query,
        final Func<ObjectName, Query<DbdColumnFields>> columns
    ) {
        this(
            rset,
            connection,
            query,
            columns,
            new SimpleObjectName(
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
     * @param columns The Func of {@link ObjectName} -> Query of DbdColumnFields to be encapsulated.
     * @param tablename The {@link ObjectName} to be encapsulated.
     */
    private TableOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTableFields> query,
        final Func<ObjectName, Query<DbdColumnFields>> columns,
        final ObjectName tablename
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
     * @param tablename The {@link ObjectName} to be encapsulated.
     */
    private TableOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTableFields> query,
        final Query<DbdColumnFields> columns,
        final ObjectName tablename
    ) {
        super(
            new ObjectTypeTable(),
            tablename,
            new ScalarEntryOfResultSet(
                DbdTableFields.DESCRIPTION,
                query,
                rset
            ),
            new ScalarEntryOfResultSet(
                DbdTableFields.OWNER,
                query,
                rset
            ),
            new ScalarEntryOfResultSet(
                DbdTableFields.TABLESPACE,
                query,
                rset
            ),
            new ScalarEntryOfResultSet(
                DbdTableFields.PARENT,
                query,
                rset
            ),
            new ScalarEntryOfResultSet(
                DbdTableFields.PARTKEYDEFINITION,
                query,
                rset
            ),
            new ScalarEntryOfResultSet(
                DbdTableFields.PARTKEYRANGE,
                query,
                rset
            ),
            new MapEntry<>(
                DbdTableFields.DEPENDENCIES,
                new YamlScalarSequenceOfResultSet(
                    DbdTableFields.DEPENDENCIES, query, rset
                )
            ),
            new MapEntry<>(
                DbdTableFields.COLUMNS,
                new DbdColumnsMappingOfEntries(
                    new ListOfResultSet<>(
                        rs -> new DbdColumnMappingOfResultSet(
                            rs,
                            columns
                        ),
                        columns,
                        connection
                    )
                )
            )
        );
    }

}
