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
package org.fusionsoft.database.snapshot.query.pg;

import org.fusionsoft.database.mapping.fields.DbdDomainConstraintFields;
import org.fusionsoft.database.snapshot.objectsignature.ObjectName;

/**
 * The only type of {@link PgMessageFormatQuery} of {@link DbdDomainConstraintFields}.
 * @since 0.1
 */
public class PgDomainConstraintsQuery extends PgMessageFormatQuery<DbdDomainConstraintFields> {

    /**
     * Instantiates a new Pg domain constraints query.
     * @param domain The FullObjectName to be encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    public PgDomainConstraintsQuery(final ObjectName domain) {
        super(
            "SELECT \n"
            + " con.conname AS {0},\n"
            + " CASE WHEN con.convalidated = 't' THEN 'true' ELSE 'false' END AS {1},\n"
            + " con.consrc AS {2}\n"
            + "FROM information_schema.domains dom\n"
            + "INNER JOIN information_schema.domain_constraints dcon \n"
            + "ON dcon.domain_schema = dom.domain_schema \n"
            + "AND dcon.domain_name = dom.domain_name\n"
            + "INNER JOIN pg_catalog.pg_constraint con \n"
            + "ON dcon.constraint_name = con.conname\n"
            + "WHERE dom.domain_schema = '" + domain.parent() + "'\n"
            + "AND dom.domain_name = '" + domain.first() + "'",
            DbdDomainConstraintFields.CONSTRAINT,
            DbdDomainConstraintFields.VALIDATED,
            DbdDomainConstraintFields.CONDITION
        );
    }

}
