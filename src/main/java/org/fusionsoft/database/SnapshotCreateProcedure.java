/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database;

import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.snapshot.AstronomicalTime;
import org.fusionsoft.database.snapshot.ConfigurationTablesOfDbd;
import org.fusionsoft.database.snapshot.DatabaseInfo;
import org.fusionsoft.database.snapshot.DatabaseInfoOfDbd;
import org.fusionsoft.database.snapshot.DbGitRepoOfDbdFile;
import org.fusionsoft.database.snapshot.DbObjects;
import org.fusionsoft.database.snapshot.HashTextOfTime;
import org.fusionsoft.database.snapshot.ObjectsFromServerMentionedInDbd;
import org.fusionsoft.database.snapshot.ObjectsWithNames;
import org.fusionsoft.database.snapshot.SnapshotFolder;
import org.fusionsoft.database.snapshot.SnapshotName;
import org.fusionsoft.database.snapshot.Writable;
import org.fusionsoft.database.snapshot.writable.DbdYamlOfObjects;
import org.fusionsoft.database.snapshot.writable.SnapshotInfo;
import org.fusionsoft.database.snapshot.writable.TableDataFilesOfObjects;

/**
 * The procedure to create database snapshot by guidance of {@link DbdFile}.
 * @since 0.1
 * @todo #39:30min Refactor and fix Qulice checks suppressions
 * @checkstyle AvoidInlineConditionalsCheck (256 lines)
 * @checkstyle ParameterNameCheck (256 lines)
 * @checkstyle ParameterNumberCheck (256 lines)
 * @checkstyle MemberNameCheck (256 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (256 lines)
 */
@SuppressWarnings("PMD")
public class SnapshotCreateProcedure {

    /**
     * The AstronomicalTime encapsulated.
     */
    private final AstronomicalTime time;

    /**
     * The DbdFile encapsulated.
     */
    private final DbdFile dbd;

    /**
     * The String encapsulated.
     */
    private final String database;

    /**
     * The Boolean encapsulated.
     */
    private final Boolean withOperationalData;

    /**
     * Instantiates a new Snapshot create procedure.
     * @param time The AstronomicalTime to be encapsulated.
     * @param dbd The DbdFile to be encapsulated.
     * @param database The String to be encapsulated.
     * @param withOperationalData The Boolean to be encapsulated.
     */
    public SnapshotCreateProcedure(
        final AstronomicalTime time,
        final DbdFile dbd,
        final String database,
        final Boolean withOperationalData
    ) {
        this.time = time;
        this.dbd = dbd;
        this.database = database;
        this.withOperationalData = withOperationalData;
    }

    /**
     * Instantiates a new Snapshot create procedure.
     * @param dbdFile The DbdFile to be encapsulated.
     * @param database The String to be encapsulated.
     * @param withOperationalData The boolean to be encapsulated.
     */
    public SnapshotCreateProcedure(
        final DbdFile dbdFile,
        final String database,
        final boolean withOperationalData
    ) {
        this(new AstronomicalTime(), dbdFile, database, withOperationalData);
    }

    /**
     * Perform.
     */
    public void perform() {
        final DatabaseInfo info = new DatabaseInfoOfDbd(
            this.dbd,
            this.database
        );
        final DbObjects objects = new ObjectsFromServerMentionedInDbd(
            info,
            this.dbd
        );
        new IterableOf<Writable>(
            new SnapshotInfo(
                new SnapshotName(this.time),
                this.time,
                info,
                this.withOperationalData
            ),
            new DbdYamlOfObjects(info, objects),
            new TableDataFilesOfObjects(this.withOperationalData
                ? objects
                : new ObjectsWithNames(
                    objects,
                    new ConfigurationTablesOfDbd(this.dbd)
                )
            )
        ).forEach(
            x -> x.writeTo(
                new SnapshotFolder(
                    new DbGitRepoOfDbdFile(this.dbd).path(),
                    new HashTextOfTime(this.time)
                )
            )
        );
    }

}
