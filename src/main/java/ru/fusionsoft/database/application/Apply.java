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

import java.nio.file.Path;
import java.sql.Connection;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.mapping.dbd.ofdbdfile.DbdServerMappingOfDbdReadable;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;
import ru.fusionsoft.lib.runnable.RunnableWithException;

/**
 * The application subcommand to resolve and apply {@link MigrationSqlFileName}
 *  in current directory over given server of DBD.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class Apply {

    /**
     * Main.
     * @param args The args with server name.
     * @throws Exception When can't.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 1;
        new Ternary<RunnableWithException<Exception>>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, needs {server name}"
            ),
            () -> {
                final Path migration = new CurrentWorkingDirectory().value().resolve(
                    new MigrationSqlFileName().asString()
                );
                Verify.main(new String[]{"console"});
                try (
                    Connection conn = new ConnectionOfDbdServerMapping(
                        new DbdServerMappingOfDbdReadable(
                            new DbdReadableOfDirectory(
                                new CurrentWorkingDirectory()
                            ),
                            new TextOf(args[0])
                        )
                    )
                ) {
                    conn.createStatement().execute(
                        new TextOf(migration).asString()
                    );
                }
            }
        ).value().run();
    }

}
