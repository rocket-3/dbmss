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

package org.fusionsoft.database.snapshot.query;

import java.text.MessageFormat;
import java.util.Comparator;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sorted;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.NumberOf;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnsOfTable;
import org.fusionsoft.database.mapping.fields.DbdColumnFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;

public class DataQuery extends BasicQuery<Text> {

    public DataQuery(final DbObject<DbdTableMapping> table) {
        this(
            table.signature().name(),
            new DbdColumnsOfTable(table)
        );
    }
    public DataQuery(
        final FullObjectName table,
        final Iterable<DbdColumnMapping> columnsMappings
    ) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format(
                    "SELECT\n {2}\nFROM {0}.{1}",
                    table.parent(),
                    table.first(),
                    new Joined(
                        new TextOf(",\n "),
                        new Mapped<Text>(
                            x -> new TextOfMappingValue(
                                x, DbdColumnFields.DBNAME
                            ),
                            new Sorted<DbdColumnMapping>(
                                Comparator.comparing(
                                    x->new NumberOf(
                                        new TextOfMappingValue(
                                            x,
                                            DbdColumnFields.ORDER
                                        )
                                    ).intValue()
                                ),
                                columnsMappings
                            )
                        )
                    ).asString()
                )
            ),
            new MapOf<>()
        );
    }

}
