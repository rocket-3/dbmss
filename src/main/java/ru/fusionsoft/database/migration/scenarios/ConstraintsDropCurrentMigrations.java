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
import java.util.Set;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Not;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.common.ConstraintDropMigration;
import ru.fusionsoft.database.migration.condition.ConstraintTypeIn;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectFieldMaybeEmpty;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.sorted.ObjectsSortedByDependencies;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * The type of that can be constructed of.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class ConstraintsDropCurrentMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Instantiates a new Constraints drop current migrations.
     * @param diff The {@link TemporalDiff} of iterables of {@link DbObject}s.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public ConstraintsDropCurrentMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms
    ) {
        super(
            () -> {
                final Set<String> partitions = new SetOf<>(
                    new Mapped<String>(
                        ot -> ot.signature().name().asString(),
                        new Filtered<>(
                            obj -> new And(
                                () -> obj.signature().type().equalsTo(
                                    new ObjectTypeTable()
                                ),
                                new Not(
                                    () -> new TextOfObjectFieldMaybeEmpty(
                                        obj, DbdTableFields.PARENT
                                    ).asString().isEmpty()
                                )
                            ).value(),
                            diff.current()
                        )
                    )
                );
                final Filtered<DbObject<DbdConstraintMapping>> cons = new Filtered<>(
                    con -> !partitions.contains(con.signature().name().parent().asString()),
                    new ObjectsWithTypeCasted<>(
                        new ObjectTypeConstraint(),
                        new ObjectsSortedByDependencies(
                            diff.current()
                        )
                    )
                );
                return new Mapped<>(
                    con -> new ConstraintDropMigration(con, dbms),
                    new Joined<>(
                        new Filtered<>(
                            con -> new ConstraintTypeIn(con, ConstraintTypeValues.FK).value(),
                            cons
                        ),
                        new Filtered<>(
                            con -> new ConstraintTypeIn(con, ConstraintTypeValues.PK).value(),
                            cons
                        ),
                        new Filtered<>(
                            con -> new Not(
                                new ConstraintTypeIn(
                                    con,
                                    ConstraintTypeValues.PK,
                                    ConstraintTypeValues.FK
                                )
                            ).value(),
                            cons
                        )
                    )
                );
            }
        );
    }

}
