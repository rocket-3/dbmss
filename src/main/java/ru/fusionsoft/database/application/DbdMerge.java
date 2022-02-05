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
import ru.fusionsoft.database.api.DbdMergeProcedure;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * The application class of updating DBD files in current directory from server, mentioned in DBD.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class DbdMerge {

    /**
     * Main.
     * @param args The array of {@link String} args, which are: {server name}.
     * @throws Exception when can't.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 1;
        new Ternary<>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, need {server name from DBD}"
            ),
            () -> new DbdMergeProcedure(
                new TextOf(args[0]),
                new CurrentWorkingDirectory()
            )
        ).value().run();
    }

}
