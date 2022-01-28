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

import java.sql.ResultSet;
import org.cactoos.map.MapEntry;
import ru.fusionsoft.database.mapping.dbd.DbdViewMapping;
import ru.fusionsoft.database.mapping.entries.MultilineSqlScalarEntry;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.database.mapping.fields.DbdViewFields;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObjectOfEntries;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectNameOfResultSet;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;
import ru.fusionsoft.database.snapshot.query.Query;
import ru.fusionsoft.lib.text.TextOfResultSet;
import ru.fusionsoft.lib.yaml.YamlScalarSequenceOfResultSet;

/**
 * The type of {@link SimpleDbObject} of {@link DbdViewMapping}
 *  that can be constructed of {@link ResultSet} and {@link Query}.
 * @since 0.1
 * @todo #40:60min Fix eo-yaml block scalars rendering
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class ViewOfResultSet extends SimpleDbObjectOfEntries<DbdViewMapping> {

    /**
     * Instantiates a new View of result set.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of {@link DbdViewFields} to be encapsulated.
     */
    public ViewOfResultSet(final ResultSet rset, final Query<DbdViewFields> query) {
        super(
            new ObjectTypeView(),
            new SimpleObjectNameOfResultSet(
                rset,
                query,
                DbdViewFields.SCHEMA,
                DbdViewFields.VIEW
            ),
            new ScalarEntry(
                DbdViewFields.OWNER,
                new TextOfResultSet(
                    query.outcomeFor(DbdViewFields.OWNER),
                    rset
                )
            ),
            new MultilineSqlScalarEntry(
                DbdViewFields.DDL,
                new TextOfResultSet(
                    query.outcomeFor(DbdViewFields.DDL),
                    rset
                )
            ),
            new MapEntry<>(
                DbdViewFields.DEPENDENCIES,
                new YamlScalarSequenceOfResultSet(
                    query.outcomeFor(DbdViewFields.DEPENDENCIES),
                    rset
                )
            )
        );
    }

}
