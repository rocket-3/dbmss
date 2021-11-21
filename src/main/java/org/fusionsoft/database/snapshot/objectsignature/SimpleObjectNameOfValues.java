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
package org.fusionsoft.database.snapshot.objectsignature;

import org.cactoos.Text;
import org.cactoos.iterable.HeadOf;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.NoNulls;
import org.cactoos.iterable.TailOf;
import org.cactoos.set.SetOf;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;

/**
 * The Text of db object names joined by {@link SimpleObjectNameDelimiter}.
 * @since 0.1
 */
public class SimpleObjectNameOfValues implements ObjectName {

    private final Iterable<Text> names;

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public SimpleObjectNameOfValues(final Iterable<Text> names) {
        this.names = names;
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public SimpleObjectNameOfValues(final Text... names) {
        this(new IterableOf<Text>(names));
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public SimpleObjectNameOfValues(final CharSequence... names) {
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
                this.names
            ).iterator().next().asString()
        );
    }

    /**
     * Parent name of object.
     * @return The parent name.
     */
    public final SimpleObjectNameOfValues parent() {
        return new SimpleObjectNameOfValues(
            new HeadOf<>(
                new SetOf<>(this.names).size() - 1,
                this.names
            )
        );
    }

    @Override
    public String asString() {
        return new UncheckedText(
            new Joined(
                new SimpleObjectNameDelimiter(),
                new NoNulls<>(names)
            )
        ).asString();
    }

}
