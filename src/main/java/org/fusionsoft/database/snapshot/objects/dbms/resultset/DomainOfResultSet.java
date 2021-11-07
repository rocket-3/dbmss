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
import org.fusionsoft.database.mapping.dbd.DbdDomainConstraintMapping;
import org.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdDomainConstraintFields;
import org.fusionsoft.database.mapping.fields.DbdDomainFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.database.snapshot.query.QueryOfScalar;
import org.fusionsoft.lib.collection.ListOfResultSet;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;
import org.fusionsoft.lib.yaml.YamlSequenceOfNodes;

/**
 * The type of {@link SimpleDbObject} of {@link DbdDomainMapping}
 *  that can be constructed of {@link ResultSet} and {@link Query} of {@link DbdDomainMapping}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class DomainOfResultSet extends SimpleDbObject<DbdDomainMapping> {

    /**
     * Instantiates a new Domain of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdDomainFields to be encapsulated.
     * @param constraints The Func of FullObjectName -> Query of DbdDomainConstraintFields
     *  to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    public DomainOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdDomainFields> query,
        final Func<FullObjectName, Query<DbdDomainConstraintFields>> constraints
    ) {
        this(
            rset,
            connection,
            query,
            constraints,
            new FullObjectName(
                new TextOfResultSet(
                    query.outcomeFor(DbdDomainFields.SCHEMA),
                    rset
                ),
                new TextOfResultSet(
                    query.outcomeFor(DbdDomainFields.DOMAIN),
                    rset
                )
            )
        );
    }

    /**
     * Instantiates a new Domain of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param connection The Connection to be encapsulated.
     * @param query The Query of DbdDomainFields to be encapsulated.
     * @param constraints The Func of FullObjectName -> Query of DbdDomainConstraintFields
     *  to be encapsulated.
     * @param domain The FullObjectName of domain to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private DomainOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdDomainFields> query,
        final Func<FullObjectName, Query<DbdDomainConstraintFields>> constraints,
        final FullObjectName domain
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
     * @param domain The FullObjectName of domain to be encapsulated.
     * @checkstyle ParameterNumberCheck (100 lines)
     */
    private DomainOfResultSet(
        final ResultSet rset,
        final Connection connection,
        final Query<DbdDomainFields> query,
        final Query<DbdDomainConstraintFields> constraints,
        final FullObjectName domain
    ) {
        super(
            new DbdDomainMapping(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
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
                            DbdDomainFields.ONWER,
                            new TextOfResultSet(
                                query.outcomeFor(DbdDomainFields.ONWER),
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
                    )
                )
            ),
            new SimpleObjectSignature(
                domain,
                ObjectType.UDT_DOMAIN
            )
        );
    }

}
