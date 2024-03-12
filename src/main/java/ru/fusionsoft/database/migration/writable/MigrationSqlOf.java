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
package ru.fusionsoft.database.migration.writable;

import org.cactoos.io.WriterTo;
import ru.fusionsoft.database.application.MigrationSqlFileName;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.Writable;
import ru.fusionsoft.lib.runnable.WithRethrowAsUnchecked;

/**
 * Writes single {@link Migration}'s text to {@link MigrationSqlFileName} file
 *  in given {@link Directory}.
 * @since 0.1
 */
public class MigrationSqlOf implements Writable {

    /**
     * The Migration encapsulated.
     */
    private final Migration migration;

    /**
     * Instantiates a new Migration sql of.
     * @param migration The {@link Migration} to be encapsulated.
     */
    public MigrationSqlOf(final Migration migration) {
        this.migration = migration;
    }

    @Override
    public final void writeTo(final Directory directory) {
        new WithRethrowAsUnchecked().exec(
            () -> {
                try (
                    WriterTo sql = new WriterTo(
                        directory.value().resolve(
                            new MigrationSqlFileName().asString()
                        )
                    )
                ) {
                    sql.write(
                        this.migration.sql().asString()
                    );
                }
            }
        );
    }

}
