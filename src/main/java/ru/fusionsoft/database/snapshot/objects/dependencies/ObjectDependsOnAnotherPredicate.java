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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Not;
import org.cactoos.scalar.Or;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.predicate.JoinedAndPredicate;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;

public class ObjectDependsOnAnotherPredicate extends JoinedAndPredicate {

    public ObjectDependsOnAnotherPredicate(final DbObject<?> source) {
        super(
            target -> new Not(() -> target.signature().equalsTo(source.signature())).value(),
            target -> new Or(
                new And(
                    () -> source.signature().type().equalsTo(new ObjectTypeTable()),
                    () -> target.signature().type().equalsTo(new ObjectTypeTable()),
                    () -> new SetOf<>(new SignaturesOfObjectDependencies(source)).contains(
                        target.signature().name()
                    )
                ),
                new And(
                    new Or(
                        (ObjectType<?> type) -> target.signature().type().equalsTo(type),
                        new IterableOf<>(
                            new ObjectTypeView(),
                            new ObjectTypeProcedure(),
                            new ObjectTypeFunction(),
                            new ObjectTypeTable()
                        )
                    ),
                    new Or(
                        (ObjectType<?> type) -> source.signature().type().equalsTo(type),
                        new IterableOf<>(
                            new ObjectTypeView(),
                            new ObjectTypeTrigger(),
                            new ObjectTypeProcedure(),
                            new ObjectTypeFunction()
                        )
                    ),
                    () -> new ObjectHasNameInSqlPredicate(source).apply(target)
                )
            ).value()
        );
    }

}
