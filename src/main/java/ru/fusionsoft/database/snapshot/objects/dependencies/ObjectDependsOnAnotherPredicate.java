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
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.predicate.JoinedAndPredicate;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectSignatureOfScalar;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;

public class ObjectDependsOnAnotherPredicate extends JoinedAndPredicate {

    public ObjectDependsOnAnotherPredicate(final ObjectSignature source) {
        super(
            target -> new Not(
                () -> target.signature().equalsTo(source)
            ).value(),
            target -> new And(
                new Or(
                    (ObjectType<?> type) -> target.signature().type().equalsTo(type),
                    new IterableOf<>(
                        new ObjectTypeView(),
                        new ObjectTypeProcedure(),
                        new ObjectTypeFunction()
                    )
                ),
                new Or(
                    (ObjectType<?> type) -> source.type().equalsTo(type),
                    new IterableOf<>(
                        new ObjectTypeView(),
                        new ObjectTypeTrigger(),
                        new ObjectTypeProcedure(),
                        new ObjectTypeFunction()
                    )
                )
            ).value(),
            new ObjectHasNameInSqlPredicate(source)
        );
    }

    public ObjectDependsOnAnotherPredicate(final DbObject<?> source) {
        this(
            new ObjectSignatureOfScalar(source::signature)
        );
    }

}
