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
package org.fusionsoft.database.snapshot.objects.signature.name;

import org.cactoos.Text;
import org.cactoos.iterable.HeadOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.NoNulls;
import org.cactoos.iterable.TailOf;
import org.cactoos.list.ListOf;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;

/**
 * The Text of db object names joined by {@link SimpleObjectNameDelimiter}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
public class SimpleObjectName implements ObjectName {

    /**
     * The UncheckedText encapsulated.
     */
    private final UncheckedText text;

    /**
     * Instantiates a new Simple object name.
     * @param text The {@link Text} to be encapsulated.
     */
    public SimpleObjectName(final Text text) {
        this.text = new UncheckedText(text);
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public SimpleObjectName(final Iterable<Text> names) {
        this(
            new Joined(
                new SimpleObjectNameDelimiter(),
                new NoNulls<>(names)
            )
        );
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public SimpleObjectName(final Text... names) {
        this(new IterableOf<Text>(names));
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public SimpleObjectName(final CharSequence... names) {
        this(new Mapped<Text>(TextOf::new, names));
    }

    /**
     * First name of object.
     * @return The first name.
     */
    public final Text first() {
        return new TextOfScalar(
            () -> new TailOf<Text>(
                1,
                new SimpleObjectNameValuesOfText(this.text)
            ).iterator().next().asString()
        );
    }

    @Override
    public final boolean equals(final ObjectName other) {
        return this.asString().equals(other.asString());
    }

    @Override
    public final String asString() {
        return this.text.asString();
    }

    /**
     * Parent name of object.
     * @return The parent name.
     */
    public final SimpleObjectName parent() {
        final ListOf<Text> names = new ListOf<>(
            new SimpleObjectNameValuesOfText(this.text)
        );
        return new SimpleObjectName(
            new HeadOf<>(
                names.size() - 1,
                names
            )
        );
    }

}
