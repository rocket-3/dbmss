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

package org.fusionsoft.database.snapshot.dbms;

import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The basic Dbms implementation.
 * @since 0.1
 */
public class AnyDbms implements Dbms {

    /**
     * The Text encapsulated.
     */
    private final Text text;

    /**
     * The The Func of Connection returns Objects to be encapsulated.
     */
    private final Func<Connection, Objects> objectsf;

    /**
     * Instantiates a new Any dbms.
     * @param text The String to be encapsulated.
     * @param objects The Func of Connection returns Objects to be encapsulated.
     */
    public AnyDbms(final String text, final Func<Connection, Objects> objects) {
        this(new TextOf(text), objects);
    }

    /**
     * Instantiates a new Any dbms.
     * @param text The Text to be encapsulated.
     * @param objects The Func of Connection returns Objects to be encapsulated.
     */
    public AnyDbms(final Text text, final Func<Connection, Objects> objects) {
        this.text = text;
        this.objectsf = objects;
    }

    @Override
    public final String asString() {
        return new UncheckedText(this.text).asString();
    }

    @Override
    public final Func<Connection, Objects> objects() {
        return this.objectsf;
    }

}
