/*
 * Copyright (C) 2018-2021 FusionSoft
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
package org.fusionsoft.database.snapshot.data;

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.mapping.value.IuTypeValues;

public class ValueFormatOfIuType extends ValueFormatOfScalar {

    public ValueFormatOfIuType(final Text type) {
        super(
            () ->
                new MapOf<String, ValueFormat>(
                    new MapEntry<>(
                        IuTypeValues.ARRAY.asString(),
                        new NotImplementedTextFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.NATIVE.asString(),
                        new NotImplementedTextFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.NUMBER.asString(),
                        new NumberValueFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.JSON.asString(),
                        new NotImplementedTextFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.BINARY.asString(),
                        new NotImplementedTextFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.BOOLEAN.asString(),
                        new NotImplementedTextFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.DATE.asString(),
                        new NotImplementedTextFormat()
                    ),
                    new MapEntry<>(
                        IuTypeValues.STRING.asString(),
                        new NotImplementedTextFormat()
                    )
                ).get(type.asString())
        );
    }

}
