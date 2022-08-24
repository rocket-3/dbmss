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

import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.scalar.And;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.text.DbdColumnIdentity;

/**
 * The type of that can be constructed of.
 * @since 0.1
 */
public class OrdersOfColumnsEquality implements Scalar<Boolean> {

    /**
     * The first Map {@link DbdColumnIdentity} to Integer encapsulated.
     */
    private final Map<DbdColumnIdentity, Integer> first;

    /**
     * The second Map {@link DbdColumnIdentity} to Integer encapsulated.
     */
    private final Map<DbdColumnIdentity, Integer> second;

    /**
     * Instantiates a new Orders of columns equality.
     * @param first The first Map {@link DbdColumnIdentity} to Integer.
     * @param second The second Map {@link DbdColumnIdentity} to Integer.
     */
    public OrdersOfColumnsEquality(
        final Map<DbdColumnIdentity, Integer> first,
        final Map<DbdColumnIdentity, Integer> second
    ) {
        this.first = first;
        this.second = second;
    }

    @Override
    public final Boolean value() {
        return new Unchecked<>(
            new And(
                entry -> new And(
                    () -> this.second.containsKey(entry.getKey()),
                    () -> this.second.get(entry.getKey()).intValue() == entry.getValue().intValue()
                ).value(),
                this.first.entrySet()
            )
        ).value();
    }

}
