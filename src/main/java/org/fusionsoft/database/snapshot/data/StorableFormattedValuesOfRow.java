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

import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Mapped;

/**
 * The {@link Iterable} of {@link String} of {@link Row} values.
 * @since 0.1
 */
public class StorableFormattedValuesOfRow extends IterableEnvelope<String> {

    /**
     * Instantiates a new Storable formatted values of row.
     * @param row The {@link Row} to be encapsulated.
     * @param cols The {@link Iterable} of {@link Column} to be encapsulated.
     */
    public StorableFormattedValuesOfRow(final Row row, final Iterable<Column> cols) {
        super(
            new Mapped<>(
                col -> col.format().yamlRepresentationOf(
                    row.textOf(col)
                ),
                cols
            )
        );
    }

}
