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
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.common.IndexDropMigration;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectFieldMaybeEmpty;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeCasted;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import ru.fusionsoft.database.snapshot.objects.sorted.ObjectsSortedByDependencies;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * Migrations to drop indexes, that are in the current database state.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
public class IndexesDropCurrentMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Ctor.
     * @param diff The {@link TemporalDiff} of Iterable of DbObject to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public IndexesDropCurrentMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms
    ) {
        super(
            () -> {
                final SetOf<String> pkeys = new SetOf<>(
                    new Mapped<>(
                        con -> con.signature().name().asString(),
                        new Filtered<>(
                            obj -> new And(
                                () -> obj.signature().type().equalsTo(new ObjectTypeConstraint()),
                                () -> new TextOfObjectFieldMaybeEmpty(
                                    obj, DbdConstraintFields.TYPE
                                ).asString().equals(
                                    ConstraintTypeValues.PK.asString()
                                )
                            ).value(),
                            diff.next()
                        )
                    )
                );
                return new Mapped<>(
                    ind -> new IndexDropMigration(ind, dbms),
                    new Filtered<>(
                        idx -> !pkeys.contains(idx.signature().name().asString()),
                        new ObjectsWithTypeCasted<>(
                            new ObjectTypeIndex(),
                            new ObjectsSortedByDependencies(
                                diff.current()
                            )
                        )
                    )
                );
            }
        );
    }

}
