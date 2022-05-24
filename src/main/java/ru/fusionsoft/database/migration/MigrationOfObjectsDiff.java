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
package ru.fusionsoft.database.migration;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.Newline;
import ru.fusionsoft.database.diff.TemporalDiff;
import ru.fusionsoft.database.migration.common.scenarios.CreateNextTablesConstraintsMigrations;
import ru.fusionsoft.database.migration.common.scenarios.CreateNextTablesIndexesMigrations;
import ru.fusionsoft.database.migration.common.scenarios.DropCurrentConstraintsMigrations;
import ru.fusionsoft.database.migration.common.scenarios.DropCurrentIndexesMigrations;
import ru.fusionsoft.database.migration.ternary.HasCurrentChangingObjectsThatSatisfy;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

public class MigrationOfObjectsDiff implements Migration {

    Iterable<Migration> migrations;

    public MigrationOfObjectsDiff(final Iterable<Migration> migrations) {
        this.migrations = migrations;
    }

    public MigrationOfObjectsDiff(final TemporalDiff<Iterable<DbObject<YamlNode>>> diff, final Dbms dbms) {
        this(
            new IterableOf<>(
                () -> {
                    final Scalar<Boolean> tchanged = new HasCurrentChangingObjectsThatSatisfy(
                        obj -> obj.signature().type().equalsTo(new ObjectTypeTable()),
                        diff
                    );
                    final Iterable<Migration> before = new Joined<>(
                        new Ternary<Iterable<Migration>>(
                            tchanged,
                            () -> new Joined<Migration>(
                                new DropCurrentIndexesMigrations(diff, dbms),
                                new DropCurrentConstraintsMigrations(diff, dbms)
                            ),
                            () -> new IterableOf<>()
                        ).value()
                    );
                    final Iterable<Migration> after = new Joined<>(
                        new Ternary<Iterable<Migration>>(
                            tchanged,
                            () -> new Joined<Migration>(
                                new CreateNextTablesConstraintsMigrations(diff, dbms),
                                new CreateNextTablesIndexesMigrations(diff, dbms)
                            ),
                            () -> new IterableOf<>()
                        ).value()
                    );
                    return new Joined<Migration>(
                        before,
                        after
                    ).iterator();
                }
            )
        );
    }

    @Override
    public Text description() {
        return new org.cactoos.text.Joined(
            new Newline(),
            new Mapped<Text>(
                Migration::description,
                this.migrations
            )
        );
    }

    @Override
    public Text sql() {
        return new org.cactoos.text.Joined(
            new Newline(),
            new Mapped<Text>(
                m -> new TextOfMessageFormat(
                    "\n-- {0} \n{1}",
                    m::description,
                    m::sql
                ),
                this.migrations
            )
        );
    }

}
