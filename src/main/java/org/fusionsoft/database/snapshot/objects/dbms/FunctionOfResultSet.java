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
import org.fusionsoft.database.mapping.dbd.DbdFunctionMapping;
import org.fusionsoft.database.mapping.entries.MultilineScalarEntry;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdFunctionFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.Query;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.MappingWithoutNullScalars;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of that can be constructed of.
 * @since 0.1
 */
public class FunctionOfResultSet extends SimpleDbObject<DbdFunctionMapping> {

    public FunctionOfResultSet(final ResultSet rset, final Query<DbdFunctionFields> query) {
        super(
            new DbdFunctionMapping(
                new MappingWithoutNullScalars(
                    new YamlMappingOfEntries(
                        new ScalarEntry(
                            DbdFunctionFields.OWNER,
                            new TextOfResultSet(
                                query.outcomeFor(DbdFunctionFields.OWNER),
                                rset
                            )
                        ),
                        new ScalarEntry(
                            DbdFunctionFields.ARGUMENTS,
                            new TextOfResultSet(
                                query.outcomeFor(DbdFunctionFields.ARGUMENTS),
                                rset
                            )
                        ),
                        new ScalarEntry(
                            DbdFunctionFields.AGGREGATE,
                            new TextOfResultSet(
                                query.outcomeFor(DbdFunctionFields.AGGREGATE),
                                rset
                            )
                        ),
                        new MultilineScalarEntry(
                            DbdFunctionFields.DDL,
                            new TextOfResultSet(
                                query.outcomeFor(DbdFunctionFields.DDL),
                                rset
                            )
                        )
                    )
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(
                    new TextOfResultSet(
                        query.outcomeFor(DbdFunctionFields.SCHEMA),
                        rset
                    ),
                    new TextOfResultSet(
                        query.outcomeFor(DbdFunctionFields.FUNCTION),
                        rset
                    )
                ),
                ObjectType.FUNCTION
            )
        );
    }

}
