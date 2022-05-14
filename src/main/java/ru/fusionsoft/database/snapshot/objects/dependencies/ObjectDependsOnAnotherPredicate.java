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

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Not;
import org.cactoos.scalar.Or;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.predicate.JoinedAndPredicate;
import ru.fusionsoft.database.snapshot.objects.predicate.TargetObjectParentIsOneOfObjectsPredicate;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;

/**
 * The predicate of {@link DbObject}, object from constructor being checked for being dependent
 *  on object from 'apply' method.
 * @param <Y> The type parameter.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 * @checkstyle LineLengthCheck (100 lines)
 */
public class ObjectDependsOnAnotherPredicate<Y extends YamlNode> extends JoinedAndPredicate<Y> {

    /**
     * Instantiates a new Object depends on another predicate.
     * @param source The {@link DbObject} which would be checked to be dependant of applied objects.
     */
    public ObjectDependsOnAnotherPredicate(final DbObject<?> source) {
        super(
            dependency -> new Not(() -> dependency.signature().equalsTo(source.signature())).value(),
            dependency -> new Or(
                new And(
                    () -> source.signature().type().equalsTo(new ObjectTypeTable()),
                    () -> dependency.signature().type().equalsTo(new ObjectTypeTable()),
                    new Or(
                        () -> new TargetObjectParentIsOneOfObjectsPredicate(dependency).apply(source),
                        () -> new SetOf<>(new SignaturesOfObjectDependencies(source)).contains(
                            dependency.signature().name()
                        )
                    )
                ),
                new And(
                    new Or(
                        (ObjectType<?> type) -> dependency.signature().type().equalsTo(type),
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
                    () -> new NameIsInTargetObjectDdlPredicate(dependency).apply(source)
                )
            ).value()
        );
    }

}
