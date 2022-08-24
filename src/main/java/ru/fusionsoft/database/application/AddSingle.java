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

import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.api.DbdAddObjectsSingleProcedure;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * This application searches DBD in working directory, takes objects of selected server,
 *  then filters them by type and name regexes, then merges them in DBD.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class AddSingle {

    /**
     * Main.
     * @param args The array of {@link String} args, which are:
     *  {server name} {type rgx} {name rgx}.
     * @throws Exception when can't.
     * @checkstyle MagicNumberCheck (100 lines)
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 3;
        new Ternary<Runnable>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, need ",
                "{server name} ",
                "{type name regexp} ",
                "{object name regexp} "
            ),
            () -> new DbdAddObjectsSingleProcedure(
                new TextOf(args[0]),
                new TextOf(args[1]),
                new TextOf(args[2]),
                new CurrentWorkingDirectory()
            )
        ).value().run();
    }

}
