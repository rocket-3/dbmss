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
package org.fusionsoft.lib.text;

import java.sql.ResultSet;
import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfString;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.snapshot.query.Query;

/**
 * The type of {@link Text} that can be constructed of {@link ResultSet} and some key.
 * @since 0.1
 */
public class TextOfResultSet extends TextEnvelope {

    /**
     * Ctor.
     * @param label The Text of key to be encapsulated.
     * @param rset The ResultSet to be encapsulated.
     */
    public TextOfResultSet(final Text label, final ResultSet rset) {
        super(
            new TextOfString(
                new UncheckedText(
                    new TextOfNullableScalar(
                        () -> rset.getString(label.asString())
                    )
                ).asString()
            )
        );
    }

    /**
     * Ctor.
     * @param label The Text subtype of key to be encapsulated.
     * @param rset The ResultSet to be encapsulated.
     * @param query The Query of Text subtype to be encapsulated.
     */
    public <T extends Text> TextOfResultSet(
        final T label,
        final ResultSet rset,
        final Query<T> query
    ) {
        this(query.outcomeFor(label), rset);
    }

    /**
     * Ctor.
     * @param label The CharSequence of key to be encapsulated.
     * @param rset The ResultSet to be encapsulated.
     */
    public TextOfResultSet(final CharSequence label, final ResultSet rset) {
        this(new TextOf(label), rset);
    }

}
