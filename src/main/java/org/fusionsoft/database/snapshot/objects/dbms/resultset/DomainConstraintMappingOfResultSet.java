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

import java.sql.ResultSet;
import org.fusionsoft.database.mapping.dbd.DbdDomainConstraintMapping;
import org.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdDomainConstraintFields;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link DbdDomainConstraintMapping}
 *  that can be constructed of {@link ResultSet} and {@link Query}.
 * @since 0.1
 */
public class DomainConstraintMappingOfResultSet extends DbdDomainConstraintMapping {

    /**
     * Instantiates a new Domain constraint mapping of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of DbdDomainConstraintFields to be encapsulated.
     */
    public DomainConstraintMappingOfResultSet(
        final ResultSet rset,
        final Query<DbdDomainConstraintFields> query
    ) {
        super(
            new MappingWithoutNullScalars(
                new YamlMappingOfEntries(
                    new ScalarEntry(
                        DbdDomainConstraintFields.CONSTRAINT,
                        new TextOfResultSet(
                            query.outcomeFor(DbdDomainConstraintFields.CONSTRAINT),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdDomainConstraintFields.VALIDATED,
                        new TextOfResultSet(
                            query.outcomeFor(DbdDomainConstraintFields.VALIDATED),
                            rset
                        )
                    ),
                    new MultilineScalarEntry(
                        DbdDomainConstraintFields.CONDITION,
                        new TextOfResultSet(
                            query.outcomeFor(DbdDomainConstraintFields.CONDITION),
                            rset
                        )
                    )
                )
            )
        );
    }

}
