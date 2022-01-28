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
package ru.fusionsoft.database.api;

import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Sticky;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.fusionsoft.database.Folder;
import ru.fusionsoft.database.WriteToFolderProcedure;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.dbdfile.DbdFileOfFolder;
import ru.fusionsoft.database.dbdfile.DbdFileOfServerSnapshotDefault;
import ru.fusionsoft.database.folder.FolderOfScalar;
import ru.fusionsoft.database.mapping.dbd.DbdServerEntry;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.writable.SnapshotFilesOfServerDefault;
import ru.fusionsoft.lib.path.CurrentWorkingDirectory;
import ru.fusionsoft.lib.path.TempFolder;

/**
 * The type of that can be constructed of.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 * @checkstyle LocalFinalVariableNameCheck (200 lines)
 */
@SuppressWarnings("PMD")
class DbdRunnableTest {

    /**
     * Merges pagilla into sakilla dbd.
     * @throws Exception When can't.
     */
    @Test
    public void mergesPagillaIntoSakillaDbd() throws Exception {
        final Folder folder = new FolderOfScalar(
            new Sticky<>(
                new TempFolder(
                    new CurrentWorkingDirectory().value().resolve("temp")
                )
            )
        );
        final Func<Text, DbdServerMapping> mkserver = server -> {
            return new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11(server),
                new CredsOfPgTestDatabase()
            );
        };
        final Text pagilla = new TextOf("pagilla");
        final Text sakilla = new TextOf("dvdrental");
        new IterableOf<>(
            new DbdCreateProcedure(
                new SnapshotFilesOfServerDefault(
                    mkserver.apply(sakilla)
                ),
                folder
            ),
            new DbdAddServerProcedure(
                new DbdServerEntry(
                    pagilla,
                    mkserver.apply(pagilla)
                ),
                folder
            ),
            new DbdMergeProcedure(
                pagilla,
                folder
            )
        ).forEach(
            WriteToFolderProcedure::run
        );
    }

    /**
     * Show diff with merged dbd file.
     * @throws Exception When can't.
     */
    @Test
    @Disabled
    public void diffWithMerged() throws Exception {
        final Folder folder = new FolderOfScalar(
            new Sticky<>(new TempFolder(new CurrentWorkingDirectory().value().resolve("temp")))
        );
        final Func<Text, DbdServerMapping> mkserver = server -> {
            return new DbdServerMappingWithCredentials(
                new UrlOfPgGitLabDatabaseV11(server),
                new CredsOfPgTestDatabase()
            );
        };
        final Text pagilla = new TextOf("pagilla");
        final Text sakilla = new TextOf("dvdrental");
        final SnapshotFilesOfServerDefault files = new SnapshotFilesOfServerDefault(
            mkserver.apply(sakilla)
        );
        final Text original = new TextOfScalar(
            () -> {
                new DbdCreateProcedure(
                    files,
                    folder
                ).run();
                return new DbdFileOfFolder(folder).asYaml().toString();
            }
        );
        final Text merged = new TextOfScalar(
            () -> {
                new IterableOf<>(
                    new DbdAddServerProcedure(
                        new DbdServerEntry(
                            pagilla,
                            mkserver.apply(pagilla)
                        ),
                        folder
                    ),
                    new DbdMergeProcedure(
                        pagilla,
                        folder
                    )
                ).forEach(
                    WriteToFolderProcedure::run
                );
                return new DbdFileOfFolder(folder).asYaml().toString();
            }
        );
        Assertions.assertEquals(
            original.asString(),
            merged.asString()
        );
    }

    /**
     * Show diff of sakilla with pagilla.
     * @throws Exception When can't.
     */
    @Test
    @Disabled
    public void diffWithPagilla() throws Exception {
        final Func<Text, Text> yamlOf = server -> new TextOfScalar(
            () -> new DbdFileOfServerSnapshotDefault(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11(server),
                    new CredsOfPgTestDatabase()
                )
            ).asYaml().toString()
        );
        final Text pagilla = new TextOf("pagilla");
        final Text sakilla = new TextOf("dvdrental");
        Assertions.assertEquals(
            yamlOf.apply(sakilla).asString(),
            yamlOf.apply(pagilla).asString()
        );
    }

}
