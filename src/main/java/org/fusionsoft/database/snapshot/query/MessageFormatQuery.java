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

/**
 * The type of {@link BasicQuery} that can be constructed
 *  of query {@link String} with template {?} points,
 *  escape character and array of outcomes in natural order.
 *  Very similar to {@link MessageFormat},
 *  but you don't need to double escape ' -> '' chars.
 * @param <T> The type of outcomes parameter.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class MessageFormatQuery<T extends Text> extends BasicQuery<T> {

    /**
     * Ctor of PgIndexesQuery with default outcomes.
     * @param text The String to be encapsulated.
     * @param quotes The String to be encapsulated.
     * @param keys The T... to be encapsulated.
     */
    @SafeVarargs
    public MessageFormatQuery(final String text, final String quotes, final T... keys) {
        this(
            new TextOf(text),
            new TextOf(quotes),
            new IterableOf<>(keys)
        );
    }

    /**
     * Ctor of PgIndexesQuery with default outcomes.
     * @param text The Text of query to be encapsulated.
     * @param quotes The Text of escape chars to be encapsulated.
     * @param keys The array of outcomes in natural order to be encapsulated.
     */
    @SafeVarargs
    public MessageFormatQuery(final Text text, final Text quotes, final T... keys) {
        this(
            text,
            quotes,
            new IterableOf<>(keys)
        );
    }

    /**
     * Ctor.
     * @param text The Text of query to be encapsulated.
     * @param quotes The Text of escape chars to be encapsulated.
     * @param keys The Iterable of keys to be encapsulated.
     */
    private MessageFormatQuery(
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
                            x -> new EscapedText(x, quotes).asString(),
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
