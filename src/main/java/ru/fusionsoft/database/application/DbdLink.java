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

import org.cactoos.io.InputOf;
import org.cactoos.io.Stdout;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.WriteTo;
import ru.fusionsoft.database.api.DbdAddServerProcedure;
import ru.fusionsoft.database.folder.CurrentWorkingDirectoryFolder;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;

/**
 * The application class of adding a server to DBD file in current directory.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
public final class DbdLink {

    /**
     * Main.
     * @param args The array of {@link String} args, which are:
     * {server name} {connectionString} {user} {password}.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 4;
        new Ternary<Runnable>(
            () -> args.length == arguments,
            () -> new WriteTo(
                new InputOf(
                    String.join(
                        "",
                        "Wrong arguments, need ",
                        "{server name} ",
                        "{jdbc:<dbms>://<url>:<port>/<catalog>} ",
                        "{user} ",
                        "{pass}"
                    )
                ),
                new Stdout()
            ),
            () -> new DbdAddServerProcedure(
                new DbdServerEntry(
                    new TextOf(args[0]),
                    new DbdServerMappingWithCredentials(
                        new TextOf(args[1]),
                        new TextOf(args[2]),
                        new TextOf(args[3])
                    )
                ),
                new CurrentWorkingDirectoryFolder()
            )
        ).value().run();
    }

}
