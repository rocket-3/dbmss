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
import javafx.application.Application;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.text.DbdFileName;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * The application command to compare current DBD to DBD taken from specific server.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class Compare {

    /**
     * Main.
     * @param args The args.
     * @throws Exception When can't.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 1;
        new Ternary<Runnable>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, needs {server name from dbd}"
            ),
            () -> {
                final Path dbd = new CurrentWorkingDirectory().value().resolve(
                    new DbdFileName().asString()
                );
                Application.launch(
                    ru.fusionsoft.database.application.gui.Compare.class,
                    dbd.toString(),
                    args[0]
                );
            }
        ).value().run();
    }

}
