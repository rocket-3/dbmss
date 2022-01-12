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
package ru.fusionsoft.database.snapshot.query;

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
import ru.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.dbd.ofobjects.DbdColumnMappingsOfTable;
import ru.fusionsoft.database.mapping.fields.DbdColumnFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.lib.yaml.artefacts.TextOfMappingValue;

/**
 * The {@link BasicQuery} for table data rows.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class DataQuery extends BasicQuery<Text> {

    /**
     * Instantiates a new Data query.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public DataQuery(final DbObject<DbdTableMapping> table) {
        this(
            table.signature().name(),
            new DbdColumnMappingsOfTable(table)
        );
    }

    /**
     * Instantiates a new Data query.
     * @param table The {@link ObjectName} to be encapsulated.
     * @param cols The {@link Iterable} of {@link DbdColumnMapping} to be encapsulated.
     */
    public DataQuery(
        final ObjectName table,
        final Iterable<DbdColumnMapping> cols
    ) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format(
                    "SELECT\n {2}\nFROM {0}.{1}",
                    table.parent().asString(),
                    table.first().asString(),
                    new Joined(
                        new TextOf(",\n "),
                        new Mapped<Text>(
                            x -> new TextOfMappingValue(
                                x, DbdColumnFields.DBNAME
                            ),
                            new Sorted<>(
                                Comparator.comparing(
                                    x -> new NumberOf(
                                        new TextOfMappingValue(x, DbdColumnFields.ORDER)
                                    ).intValue()
                                ),
                                cols
                            )
                        )
                    ).asString()
                )
            ),
            new MapOf<>()
        );
    }

}
