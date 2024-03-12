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
package ru.fusionsoft.database.application;

import java.util.Map;
import org.cactoos.Proc;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapOf;
import ru.fusionsoft.lib.runnable.WithRethrowAsUnchecked;

/**
 * The enum Commands.
 */
@SuppressWarnings("PMD")
public enum Commands {
    /**
     * The Commands encapsulated.
     */
    INIT(
        "init",
        "<no args>\n - create new DBD document here",
        Init::main
    ),
    /**
     * The Commands encapsulated.
     */
    INIT_OF(
        "initof",
        "<jdbc:url> <user> <pass>\n - create new DBD document here of provided server credentials",
        InitOf::main
    ),
    /**
     * The Commands encapsulated.
     */
    LINK(
        "link",
        "<new server name> <jdbc:url> <user> <pass>\n - add new server to the DBD",
        Link::main
    ),
    /**
     * The Commands encapsulated.
     */
    MERGE(
        "merge",
        "<server name>\n - update objects, stated in current DBD of server specified",
        Merge::main
    ),
    /**
     * The Commands encapsulated.
     */
    ADD(
        "add",
        "<server name> <type regex> <name regex>\n - add or update objects of server specified",
        AddSingle::main
    ),
    /**
     * The Commands encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    ADDGRAPH(
        "addgraph",
        "<server name> <type regex> <name regex>\n - add or update objects with dependencies of"
        + " server specified",
        AddGraph::main
    ),
    /**
     * The Commands encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    SNAPHOT(
        "snapshot",
        "<server name> <with operational data: true/false>\n - take snapshot of server specified in"
        + " separate folder",
        Snapshot::main
    ),
    /**
     * The Commands encapsulated.
     */
    MIGRATION(
        "migration",
        String.join(
            " ",
            "<server name>\n",
            "- generate",
            new MigrationSqlFileName().asString(),
            "to merge the server into the DBD state"
        ),
        Migration::main
    ),
    /**
     * The Commands encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines)
     */
    MIGRATION_FILES(
        "migration-files",
        String.join(
            " ",
            "<POSTGRES|MYSQL|MSSQL|ORACLE> <config file> <DBD from> <DBD next>\n",
            "- generate",
            new MigrationSqlFileName().asString(),
            "to merge FROM state to NEXT state"
        ),
        MigrationFiles::main
    ),
    /**
     * The Commands encapsulated.
     */
    VERIFY(
        "verify",
        String.join(
            " ",
            "<type: gui|console>\n",
            "- check ",
            new MigrationSqlFileName().asString()
        ),
        Verify::main
    ),
    /**
     * The Commands encapsulated.
     */
    COMPARE(
        "compare",
        "<server name>\n - compare current DBD over given server",
        Compare::main
    ),
    /**
     * The Commands encapsulated.
     */
    APPLY(
        "apply",
        String.join(
            " ",
            "<server name>\n",
            "- apply",
            new MigrationSqlFileName().asString(),
            "to the given server"
        ),
        Apply::main
    );

    /**
     * The name of command.
     */
    private final String name;

    /**
     * The description of command.
     */
    private final String description;

    /**
     * The {@link Proc} of command.
     */
    private final Proc<String[]> proc;

    /**
     * Instantiates a new Commands.
     * @param name The name of command to be encapsulated.
     * @param description The description of command to be encapsulated.
     * @param proc The {@link Proc} of command to be encapsulated.
     */
    Commands(final String name, final String description, final Proc<String[]> proc) {
        this.name = name;
        this.proc = proc;
        this.description = description;
    }

    /**
     * Command string.
     * @return The string.
     */
    public String command() {
        return this.name;
    }

    /**
     * Description string.
     * @return The string.
     */
    public String description() {
        return this.description;
    }

    /**
     * Apply.
     * @param args The args.
     */
    public void apply(final String[] args) {
        new WithRethrowAsUnchecked().exec(
            () -> this.proc.exec(args)
        );
    }

    /**
     * Map map.
     * @return The map.
     */
    public static Map<String, Proc<String[]>> map() {
        return new MapOf<>(
            item -> item.command(),
            item -> item::apply,
            new IterableOf<>(Commands.values())
        );
    }
}
