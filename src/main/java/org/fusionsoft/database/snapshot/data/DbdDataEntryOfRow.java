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
package org.fusionsoft.database.snapshot.data;

import com.amihaiemil.eoyaml.Scalar;
import com.amihaiemil.eoyaml.Yaml;
import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.mapping.entries.YamlMappingEntryOfScalar;

public class DbdDataEntryOfRow extends YamlMappingEntryOfScalar<Scalar> {

    public DbdDataEntryOfRow(final Row row, final Iterable<Column> cols) {
        super(
            new TextOfScalar(() -> String.valueOf(row.number())),
            () -> Yaml.createYamlInput(
                MessageFormat.format(
                    "[{0}]",
                    new Joined(
                        new TextOf(", "),
                        new Mapped<Text>(
                            col -> col.format().storableRepresentationOf(
                                row.textOf(col)
                            ),
                            cols
                        )
                    ).asString()
                )
            ).readPlainScalar()
        );
    }

}
