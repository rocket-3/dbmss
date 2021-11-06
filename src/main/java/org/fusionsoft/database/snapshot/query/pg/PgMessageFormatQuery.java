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

import org.cactoos.Text;
import org.fusionsoft.database.snapshot.query.MessageFormatQuery;

/**
 * The type of {@link MessageFormatQuery} with default {@link PgEscapeSymbol} as 'quotes' argument.
 * @param <T> The type of fields to query parameter.
 * @since 0.1
 */
public class PgMessageFormatQuery<T extends Text> extends MessageFormatQuery<T> {

    /**
     * Instantiates a new Pg simple query.
     * @param text The Text of query to be encapsulated.
     * @param keys The Text array of outcomes keys to be encapsulated.
     */
    @SafeVarargs
    public PgMessageFormatQuery(final Text text, final T... keys) {
        super(text, new PgEscapeSymbol(), keys);
    }

    /**
     * Instantiates a new Pg simple query.
     * @param text The String of query to be encapsulated.
     * @param keys The Text array of outcomes keys to be encapsulated.
     */
    @SafeVarargs
    public PgMessageFormatQuery(final String text, final T... keys) {
        super(text, new PgEscapeSymbol().asString(), keys);
    }

}
