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
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.scalar.Ternary;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Migrations to create all constraints and indexes that exist in 'current' state.
 * @since 0.1
 */
public class ConstraintsAndIndexesDropMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Instantiates a new Constraints and indexes drop migrations.
     * @param diff The {@link TemporalDiff} of iterables of {@link DbObject}s.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public ConstraintsAndIndexesDropMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms
    ) {
        this(new ObjectsDiff(diff), dbms);
    }

    /**
     * Instantiates a new Constraints and indexes drop migrations.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    @SuppressWarnings("PMD")
    public ConstraintsAndIndexesDropMigrations(
        final ObjectsDiff diff,
        final Dbms dbms
    ) {
        super(
            () -> {
                return new Ternary<>(
                    true,
                    () -> new Joined<Migration>(
                        new ConstraintsDropCurrentMigrations(diff, dbms),
                        new IndexesDropCurrentMigrations(diff, dbms)
                    ),
                    IterableOf<Migration>::new
                ).value();
            }
        );
    }

}
