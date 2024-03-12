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
package ru.fusionsoft.database.snapshot.data;

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.mapping.values.IuTypeValues;

/**
 * The {@link ValueFormatOfScalar} which uses different {@link ValueFormat}s inside,
 *  can be constructed from any {@link IuTypeValues}'s {@link Text}.
 * @since 0.1
 */
public class ValueFormatOfIuType extends ValueFormatOfScalar {

    /**
     * Instantiates a new Value format of iu type.
     * @param type The {@link Text} to be encapsulated.
     */
    public ValueFormatOfIuType(final Text type) {
        super(
            () ->
                new MapOf<String, ValueFormat>(
                    new MapEntry<>(
                        IuTypeValues.ARRAY.asString(),
                        new ValueFormatDummy()
                    ),
                    new MapEntry<>(
                        IuTypeValues.NATIVE.asString(),
                        new ValueFormatDummy()
                    ),
                    new MapEntry<>(
                        IuTypeValues.NUMBER.asString(),
                        new ValueFormatNumber()
                    ),
                    new MapEntry<>(
                        IuTypeValues.JSON.asString(),
                        new ValueFormatDummy()
                    ),
                    new MapEntry<>(
                        IuTypeValues.BINARY.asString(),
                        new ValueFormatDummy()
                    ),
                    new MapEntry<>(
                        IuTypeValues.BOOLEAN.asString(),
                        new ValueFormatDummy()
                    ),
                    new MapEntry<>(
                        IuTypeValues.DATE.asString(),
                        new ValueFormatDummy()
                    ),
                    new MapEntry<>(
                        IuTypeValues.STRING.asString(),
                        new ValueFormatDummy()
                    )
                ).get(type.asString())
        );
    }

}
