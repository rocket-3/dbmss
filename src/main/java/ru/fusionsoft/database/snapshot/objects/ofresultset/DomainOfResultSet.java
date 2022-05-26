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
import ru.fusionsoft.database.mapping.dbd.DbdDomainConstraintMapping;
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.mapping.dbd.ofresultset.DomainConstraintMappingOfResultSet;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdDomainConstraintFields;
import ru.fusionsoft.database.mapping.fields.DbdDomainFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeDomain;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.database.snapshot.query.QueryOfScalar;
import ru.fusionsoft.lib.collection.ListOfResultSet;
import ru.fusionsoft.lib.text.TextOfResultSet;
import ru.fusionsoft.lib.yaml.YamlSequenceOfNodes;

/**
 * The type of {@link SimpleDbObject} of {@link DbdDomainMapping}
 *  that can be constructed of {@link ResultSet} and {@link Query} of {@link DbdDomainMapping}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class DomainOfResultSet extends SimpleDbObjectOfEntries<DbdDomainMapping> {

    /**
     * Instantiates a new Domain of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdDomainFields to be encapsulated.
     * @param constraints The Func of {@link ObjectName} -> Query of DbdDomainConstraintFields
     *  to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public DomainOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdDomainFields> query,
        final Func<ObjectName, Query<DbdDomainConstraintFields>> constraints
    ) {
        this(
            rset,
            connection,
            query,
            constraints,
            new SimpleObjectNameOfResultSet(
                rset,
                query,
                DbdDomainFields.SCHEMA,
                DbdDomainFields.DOMAIN
            )
        );
    }

    /**
     * Instantiates a new Domain of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdDomainFields to be encapsulated.
     * @param constraints The Func of {@link ObjectName} -> Query of DbdDomainConstraintFields
     *  to be encapsulated.
     * @param domain The FullObjectName of domain to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private DomainOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdDomainFields> query,
        final Func<ObjectName, Query<DbdDomainConstraintFields>> constraints,
        final ObjectName domain
    ) {
        this(
            rset,
            connection,
            query,
            new QueryOfScalar<>(() -> constraints.apply(domain)),
            domain
        );
    }

    /**
     * Instantiates a new Domain of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdDomainFields to be encapsulated.
     * @param constraints The Query of DbdDomainConstraintFields to be encapsulated.
     * @param domain The {@link ObjectName} of domain to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private DomainOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdDomainFields> query,
        final Query<DbdDomainConstraintFields> constraints,
        final ObjectName domain
    ) {
        super(
            new ObjectTypeDomain(),
            domain,
            new ScalarEntry(
                DbdDomainFields.TYPE,
                new TextOfResultSet(
                    query.outcomeFor(DbdDomainFields.TYPE),
                    rset
                )
            ),
            new ScalarEntry(
                DbdDomainFields.DEFAULT,
                new TextOfResultSet(
                    query.outcomeFor(DbdDomainFields.DEFAULT),
                    rset
                )
            ),
            new ScalarEntry(
                DbdDomainFields.DESCRIPTION,
                new TextOfResultSet(
                    query.outcomeFor(DbdDomainFields.DESCRIPTION),
                    rset
                )
            ),
            new ScalarEntry(
                DbdDomainFields.OWNER,
                new TextOfResultSet(
                    query.outcomeFor(DbdDomainFields.OWNER),
                    rset
                )
            ),
            new MapEntry<>(
                DbdDomainFields.CONSTRAINTS,
                new YamlSequenceOfNodes(
                    new ListOfResultSet<DbdDomainConstraintMapping>(
                        (ResultSet rs) -> new DomainConstraintMappingOfResultSet(
                            rs,
                            constraints
                        ),
                        constraints,
                        connection
                    )
                )
            )
        );
    }

}
