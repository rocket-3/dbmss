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
package org.fusionsoft.database.snapshot.objects;

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.Joined;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;

/**
 * The Text of db object names joined.
 * @since 0.1
 */
public class NamesJoined extends TextEnvelope {

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public NamesJoined(final Iterable<Text> names) {
        super(new Joined(new TextOf("$"), names));
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public NamesJoined(final Text... names) {
        this(new IterableOf<Text>(names));
    }

    /**
     * Ctor.
     * @param names The names to be joined.
     */
    public NamesJoined(final CharSequence... names) {
        this(new Mapped<Text>(TextOf::new, names));
    }

}
