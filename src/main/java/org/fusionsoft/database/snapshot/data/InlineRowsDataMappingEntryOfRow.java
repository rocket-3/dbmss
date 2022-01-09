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
package org.fusionsoft.database.snapshot.data;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlNode;
import java.text.MessageFormat;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.entries.YamlMappingEntryOfScalar;

/**
 * The entries for {@link org.fusionsoft.database.mapping.dbd.DbdDataMapping}
 *  with '"key" : ["value1", "value2"]' format.
 * @since 0.1
 */
public class InlineRowsDataMappingEntryOfRow extends YamlMappingEntryOfScalar<YamlNode> {

    /**
     * Instantiates a new Rows data mapping entry of row.
     * @param row The {@link Row} to be encapsulated.
     * @param cols The {@link Iterable} of {@link Column} to be encapsulated.
     */
    public InlineRowsDataMappingEntryOfRow(final Row row, final Iterable<Column> cols) {
        super(
            new TextOfScalar(row::label),
            () -> Yaml.createYamlInput(
                MessageFormat.format(
                    "[{0}]",
                    new Joined(
                        ", ",
                        new StorableFormattedValuesOfRow(row, cols)
                    ).asString()
                )
            ).readPlainScalar()
        );
    }

}
