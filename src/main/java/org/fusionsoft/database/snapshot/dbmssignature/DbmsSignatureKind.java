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
package org.fusionsoft.database.snapshot.dbmssignature;

import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.scalar.ScalarOf;
import org.cactoos.text.UncheckedText;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsOfScalar;

/**
 * The DbmsSignatureName representing DBMS name related behavior.
 * @since 0.1
 */
public abstract class DbmsSignatureKind implements Text {

    /**
     * The String encapsulated.
     */
    private final UncheckedText text;

    /**
     * The Func of Connection and Objects encapsulated.
     */
    private final Func<Connection, Objects> func;

    /**
     * Instantiates a new Dbms signature name.
     * @param text The String of name to be encapsulated.
     * @param func The Func of Connection returns Objects to be encapsulated.
     */
    public DbmsSignatureKind(
        final Text text,
        final Func<Connection, Objects> func
    ) {
        this.text = new UncheckedText(text);
        this.func = func;
    }

    @Override
    public final String asString() {
        return this.text.asString();
    }

    /**
     * Objects objects.
     * @param connection The connection.
     * @return The objects.
     */
    public final Objects objects(final Connection connection) {
        return new ObjectsOfScalar(
            new ScalarOf<>(
                this.func,
                connection
            )
        );
    }

}
