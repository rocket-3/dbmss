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
import ru.fusionsoft.database.api.MigrateDbdToDbdProcedure;
import ru.fusionsoft.database.dbdreadable.DbdReadableOfDirectory;
import ru.fusionsoft.database.mapping.config.MigrationConfigMappingOfDirectory;
import ru.fusionsoft.database.migration.diff.SimpleTemporalDiff;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfText;
import ru.fusionsoft.lib.input.ConsolePrinting;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.runnable.ExitWithError;

/**
 * The application command to create migration sql between two files.
 * @since 0.1
 * @checkstyle HideUtilityClassConstructorCheck (100 lines)
 * @checkstyle MagicNumberCheck (100 lines)
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
@SuppressWarnings("PMD")
public final class MigrationFiles {

    /**
     * Main.
     * @param args The array of {@link String} args, which are:
     *  {file from}, {file to}, {DBMS name}, {migration config file}
     * @throws Exception when can't.
     */
    public static void main(final String[] args) throws Exception {
        final int arguments = 4;
        final CurrentWorkingDirectory directory = new CurrentWorkingDirectory();
        new Ternary<>(
            () -> args.length != arguments,
            () -> new ExitWithError(
                "Wrong arguments, need {POSTGRES|MYSQL|MSSQL|ORACLE)}, "
                + "{migration config file}, {file from}, {file to}"
            ),
            () -> (Runnable) () -> {
                new MigrateDbdToDbdProcedure(
                    new SimpleTemporalDiff<>(
                        new DbdReadableOfDirectory(directory, new TextOf(args[2])),
                        new DbdReadableOfDirectory(directory, new TextOf(args[3]))
                    ),
                    new DbmsOfText(new TextOf(args[0])),
                    new MigrationConfigMappingOfDirectory(directory, new TextOf(args[1])),
                    directory
                ).run();
                new ConsolePrinting(new TextOf("Done.")).run();
            }
        ).value().run();
    }

}
