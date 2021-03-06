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
import ru.fusionsoft.database.mapping.dbd.DbdTupleAttributeMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTupleMapping;
import ru.fusionsoft.database.mapping.dbd.ofresultset.TupleAttributeMappingOfResultSet;
import ru.fusionsoft.database.mapping.entries.ScalarEntryOfResultSet;
import ru.fusionsoft.database.mapping.fields.DbdTupleAttributeField;
import ru.fusionsoft.database.mapping.fields.DbdTupleField;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTuple;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.database.snapshot.query.QueryOfScalar;
import ru.fusionsoft.lib.collection.ListOfConnection;
import ru.fusionsoft.lib.yaml.YamlSequenceOfNodes;

/**
 * The type of {@link SimpleDbObjectOfEntries} of {@link ResultSet}, {@link Connection},
 * {@link DbdTupleField} and {@link DbdTupleAttributeField} {@link Query}'s.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (200 lines)
 */
public class TupleOfResultSet extends SimpleDbObjectOfEntries<DbdTupleMapping> {

    /**
     * Instantiates a new Tuple of result set.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     * @param query The {@link Query} of {@link DbdTupleField} to be encapsulated.
     * @param attributes The {@link Func} of {@link SimpleObjectName} ->
     *  {@link Query} of {@link DbdTupleAttributeField} to be encapsulated.
     */
    public TupleOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTupleField> query,
        final Func<ObjectName, Query<DbdTupleAttributeField>> attributes
    ) {
        this(
            rset,
            connection,
            query,
            attributes,
            new SimpleObjectNameOfResultSet(
                rset, query,
                DbdTupleField.SCHEMA,
                DbdTupleField.TUPLE
            )
        );
    }

    /**
     * Instantiates a new Tuple of result set.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     * @param query The {@link Query} of {@link DbdTupleField} to be encapsulated.
     * @param attributes The {@link Func} of {@link SimpleObjectName} ->
     *  {@link Query} of {@link DbdTupleAttributeField} to be encapsulated.
     * @param tuple The {@link ObjectName} of the tuple to be encapsulated.
     */
    private TupleOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTupleField> query,
        final Func<ObjectName, Query<DbdTupleAttributeField>> attributes,
        final ObjectName tuple
    ) {
        this(
            rset,
            connection,
            query,
            new QueryOfScalar<>(() -> attributes.apply(tuple)),
            tuple
        );
    }

    /**
     * Instantiates a new Tuple of result set.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     * @param query The {@link Query} of {@link DbdTupleField} to be encapsulated.
     * @param tuples The {@link Query} of {@link DbdTupleAttributeField} to be encapsulated.
     * @param tuple The {@link ObjectName} of the tuple to be encapsulated.
     */
    private TupleOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdTupleField> query,
        final Query<DbdTupleAttributeField> tuples,
        final ObjectName tuple
    ) {
        super(
            new ObjectTypeTuple(),
            tuple,
            new ScalarEntryOfResultSet(DbdTupleField.OWNER, rset, query),
            new ScalarEntryOfResultSet(DbdTupleField.DESCRIPTION, rset, query),
            new MapEntry<>(
                DbdTupleField.ATTRIBUTES,
                new YamlSequenceOfNodes(
                    new ListOfConnection<DbdTupleAttributeMapping>(
                        connection,
                        tuples,
                        TupleAttributeMappingOfResultSet::new
                    )
                )
            )
        );
    }

}
