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
package ru.fusionsoft.database.mapping.dbd.ofresultset;

import java.sql.ResultSet;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.JsonUndefinedText;
import ru.fusionsoft.lib.text.TextOfResultSet;
import ru.fusionsoft.lib.yaml.MappingWithoutNullScalarsNested;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link DbdColumnMapping} that can be constructed
 *  of {@link Query} and {@link ResultSet}.
 * @since 0.1
 */
public class DbdColumnMappingOfResultSet extends DbdColumnMapping {

    /**
     * Instantiates a new Simple db object.
     * @param rset The {@link ResultSet} to be encapsulated.
     * @param query The {@link Query} of {@link DbdColumnFields} to be encapsulated.
     */
    public DbdColumnMappingOfResultSet(final ResultSet rset, final Query<DbdColumnFields> query) {
        super(
            new MappingWithoutNullScalarsNested(
                new YamlMappingOfEntries(
                    new ScalarEntry(
                        DbdColumnFields.DBNAME,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.DBNAME),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.DBMSTYPE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.DBMSTYPE),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.DBNULLABLE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.DBNULLABLE),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.DESCRIPTION,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.DESCRIPTION),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.DEFAULT,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.DEFAULT),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.IUTYPE,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.IUTYPE),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.ORDER,
                        new TextOfResultSet(
                            query.outcomeFor(DbdColumnFields.ORDER),
                            rset
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.IUCOLUMN,
                        new TextOfScalar(
                            () -> new JsonUndefinedText().asString()
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.IUJSONCOLUMN,
                        new TextOfScalar(
                            () -> new JsonUndefinedText().asString()
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.IUINLUDEPROPS,
                        new TextOfScalar(
                            () -> new JsonUndefinedText().asString()
                        )
                    ),
                    new ScalarEntry(
                        DbdColumnFields.IUINCLUDEREST,
                        new TextOfScalar(
                            () -> new JsonUndefinedText().asString()
                        )
                    )
                )
            )
        );
    }

}
