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
import java.util.Iterator;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.lib.text.EscapedText;

public class SimpleQuery<T extends Text> extends BasicQuery<T> {

    /**
     * Ctor of PgIndexesQuery with default outcomes.
     */
    @SafeVarargs
    public SimpleQuery(final String text, final String quotes, final T... keys) {
        this(
            new TextOf(text),
            new TextOf(quotes),
            new IterableOf<>(keys)
        );
    }

    /**
     * Ctor of PgIndexesQuery with default outcomes.
     */
    @SafeVarargs
    public SimpleQuery(final Text text, final Text quotes, final T... keys) {
        this(
            text,
            quotes,
            new IterableOf<>(keys)
        );
    }

    /**
     * Ctor.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    private SimpleQuery(
        final Text text,
        final Text quotes,
        final Iterable<T> keys
    ) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format(
                    text.asString().replace("'", "''"),
                    new ListOf<>(
                        new Mapped<String>(
                            x->new EscapedText(x, quotes).asString(),
                            keys
                        )
                    ).toArray()
                )
            ),
            new MapOf<>(
                new IterableOf<>(
                    () -> {
                        final Iterator<String> iter = new Mapped<>(
                            Text::asString, keys
                        ).iterator();
                        return new Mapped<>(
                            key -> new MapEntry<>(key, iter.next()),
                            keys
                        ).iterator();
                    }
                )
            )
        );
    }

}
