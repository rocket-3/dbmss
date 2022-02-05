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

import org.cactoos.Func;
import org.cactoos.Output;
import org.cactoos.io.Stdout;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.UncheckedTempFolder;
import ru.fusionsoft.lib.runnable.ProcessRunnable;
import ru.fusionsoft.lib.runnable.process.ArgsExplicit;
import ru.fusionsoft.lib.runnable.process.ArgsRunningExecutable;

/**
 * The tests for @{@link ru.fusionsoft.database.application} package.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (150 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class ApplicationTest {

    /**
     * Runs something.
     * @throws Exception When can't.
     */
    @Test
    public void runsSomething() throws Exception {
        final Directory directory = new UncheckedTempFolder();
        final Func<String, Directory> executable = app -> {
            return () -> new CurrentWorkingDirectory()
                .value()
                .resolve("target")
                .resolve("api")
                .resolve("bin")
                .resolve(app);
        };
        final Output output = new Stdout();
        for (final Runnable runnable : new IterableOf<Runnable>(
            new ProcessRunnable(
                new CurrentWorkingDirectory(),
                new ArgsRunningExecutable(
                    new TextOf("mvn"),
                    new ArgsExplicit(
                        "clean",
                        "install",
                        "-P",
                        "api",
                        "-D",
                        "skipTests"
                    )
                ),
                output
            ),
            new ProcessRunnable(
                directory,
                new ArgsRunningExecutable(
                    executable.apply("dbdnew"),
                    new ArgsExplicit(
                        new UrlOfPgGitLabDatabaseV11("dvdrental").asString(),
                        new CredsOfPgTestDatabase().username(),
                        new CredsOfPgTestDatabase().password()
                    )
                ),
                output
            ),
            new ProcessRunnable(
                directory,
                new ArgsRunningExecutable(
                    executable.apply("dbdlink"),
                    new ArgsExplicit(
                        "pagilla",
                        new UrlOfPgGitLabDatabaseV11("pagilla").asString(),
                        new CredsOfPgTestDatabase().username(),
                        new CredsOfPgTestDatabase().password()
                    )
                ),
                output
            ),
            new ProcessRunnable(
                directory,
                new ArgsRunningExecutable(
                    executable.apply("dbdsnap"),
                    new ArgsExplicit(
                        "pagilla",
                        "false"
                    )
                ),
                output
            ),
            new ProcessRunnable(
                directory,
                new ArgsRunningExecutable(
                    executable.apply("dbdmerge"),
                    new ArgsExplicit(
                        "pagilla"
                    )
                ),
                output
            )
        )) {
            runnable.run();
        }
    }

}
