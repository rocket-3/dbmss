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
package ru.fusionsoft.database.migration;

import java.util.List;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.Joined;
import org.cactoos.text.Newline;
import org.cactoos.text.TextOf;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The {@link Migration} of iterable of {@link Migration}s, with common description added.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines).
 */
public class MigrationJoined implements Migration {

    /**
     * The Text encapsulated.
     */
    private final Text label;

    /**
     * The Migrations encapsulated.
     */
    private final Iterable<Migration> migrations;

    /**
     * Instantiates a new Migration joined.
     * @param label The {@link Text} to be encapsulated.
     * @param migrations The Migrations iterable to be encapsulated.
     */
    public MigrationJoined(final Text label, final Iterable<Migration> migrations) {
        this.label = label;
        this.migrations = migrations;
    }

    /**
     * Instantiates a new Migration joined.
     * @param label The {@link String} to be encapsulated.
     * @param migrations The Migrations iterable to be encapsulated.
     */
    public MigrationJoined(final String label, final Iterable<Migration> migrations) {
        this(new TextOf(label), migrations);
    }

    @Override
    public final Text description() {
        return new Joined(
            new Newline(),
            new org.cactoos.iterable.Joined<>(
                this.label,
                new Mapped<Text>(
                    migration -> new TextOfMessageFormat(
                        "  {0}",
                        migration.description()
                    ),
                    this.migrations
                )
            )
        );
    }

    @Override
    public final Text sql() {
        final List<Migration> list = new ListOf<>(this.migrations);
        return new Unchecked<>(
            new Ternary<>(
                list::isEmpty,
                () -> new TextOf(""),
                () -> new Joined(
                    new Newline(),
                    new org.cactoos.iterable.Joined<>(
                        new TextOfMessageFormat("--- {0}", this.label),
                        new Mapped<Text>(
                            migration -> new TextOfMessageFormat(
                                "\n-- {0}\n{1}",
                                migration.description(),
                                migration.sql()
                            ),
                            this.migrations
                        )
                    )
                )
            )
        ).value();
    }

}
