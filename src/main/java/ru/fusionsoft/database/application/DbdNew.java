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
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.WriteTo;
import ru.fusionsoft.database.api.DbdCreateProcedure;
import ru.fusionsoft.database.folder.CurrentWorkingDirectoryFolder;
import ru.fusionsoft.database.snapshot.writable.SnapshotFilesOfServerDefault;

/**
 * The application class of just creating a bare snapshot with a new DBD and fetching all the data.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class DbdNew {

    /**
     * Main.
     * @param args The array of {@link String} args, which are: connectionString user password.
     */
    public static void main(final String[] args) {
        final int arguments = 3;
        if (args.length != arguments) {
            new WriteTo(
                new InputOf(
                    "Wrong arguments, need {jdbc:<dbms>://<url>:<port>/<catalog>} {user} {pass}"
                ),
                new Stdout()
            ).run();
        } else {
            new DbdCreateProcedure(
                new SnapshotFilesOfServerDefault(
                    new TextOf(args[0]),
                    new TextOf(args[1]),
                    new TextOf(args[2])
                ),
                new CurrentWorkingDirectoryFolder()
            ).run();
        }
    }

}
