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

import org.cactoos.scalar.ScalarOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.api.SnapshotCreateProcedure;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * The application class of creating a DBD snapshot from server mentioned in DBD
 *  in new folder in the current directory.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class Snapshot {

    /**
     * Main.
     * @param args The array of {@link String} args, which are:
     *  {server name} {with op data true/false}.
     * @throws Exception when can't.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 2;
        new Ternary<Runnable>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, need {server name from DBD} {with op data true\\false}"
            ),
            () -> new SnapshotCreateProcedure(
                new TextOf(args[0]),
                new CurrentWorkingDirectory(),
                new ScalarOf<>(() -> Boolean.valueOf(args[1])).value()
            )
        ).value().run();
    }

}
