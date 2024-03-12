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
package ru.fusionsoft.database.migration.scenarios;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Reversed;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.common.AnyObjectCreateMigration;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithSqlOrigin;
import ru.fusionsoft.database.snapshot.objects.sorted.ObjectsSortedByTypeAndDependencies;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Migrations to create views, functions, triggers, that are in next database state.
 * @since 0.1
 */
public class SqlsCreateMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Instantiates a new Sqls create migrations.
     * @param diff The {@link TemporalDiff} of Iterable of {@link DbObject}s to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public SqlsCreateMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms
    ) {
        this(new ObjectsDiff(diff), dbms);
    }

    /**
     * Instantiates a new Sqls create migrations.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public SqlsCreateMigrations(
        final ObjectsDiff diff,
        final Dbms dbms
    ) {
        super(
            () -> {
                return new Mapped<>(
                    obj -> new AnyObjectCreateMigration(obj, dbms),
                    new Reversed<>(
                        new ObjectsSortedByTypeAndDependencies(
                            new ObjectsWithSqlOrigin(diff.next())
                        )
                    )
                );
            }
        );
    }

}
