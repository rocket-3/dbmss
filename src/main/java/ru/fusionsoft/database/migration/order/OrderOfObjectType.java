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
package ru.fusionsoft.database.migration.order;

import org.cactoos.Func;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeData;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeDomain;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeEnum;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSchema;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSequence;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTablespace;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTuple;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;

/**
 * The numbers associated with different {@link ObjectType}s.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 * @checkstyle MagicNumberCheck (200 lines)
 */
public class OrderOfObjectType implements Func<ObjectType<?>, Integer> {

    @Override
    public final Integer apply(final ObjectType<?> input) {
        final MapOf<ObjectType<?>, Integer> map = new MapOf<>(
            new MapEntry<>(
                new ObjectTypeTablespace(),
                5
            ),
            new MapEntry<>(
                new ObjectTypeSchema(),
                10
            ),
            new MapEntry<>(
                new ObjectTypeTuple(),
                11
            ),
            new MapEntry<>(
                new ObjectTypeEnum(),
                12
            ),
            new MapEntry<>(
                new ObjectTypeDomain(),
                13
            ),
            new MapEntry<>(
                new ObjectTypeSequence(),
                20
            ),
            new MapEntry<>(
                new ObjectTypeTable(),
                30
            ),
            new MapEntry<>(
                new ObjectTypeProcedure(),
                60
            ),
            new MapEntry<>(
                new ObjectTypeFunction(),
                70
            ),
            new MapEntry<>(
                new ObjectTypeTrigger(),
                80
            ),
            new MapEntry<>(
                new ObjectTypeView(),
                100
            ),
            new MapEntry<>(
                new ObjectTypeData(),
                200
            )
        );
        return new Unchecked<>(
            new Ternary<>(
                () -> map.containsKey(input),
                () -> map.get(input),
                () -> 1000
            )
        ).value();
    }

}
