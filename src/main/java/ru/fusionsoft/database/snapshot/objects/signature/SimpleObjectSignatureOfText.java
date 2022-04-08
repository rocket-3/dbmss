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
package ru.fusionsoft.database.snapshot.objects.signature;

import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.objects.signature.name.ObjectNameOfScalar;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeOfScalar;

/**
 * The {@link SimpleObjectSignature} can be parsed of {@link Text}.
 * @since 0.1
 */
public class SimpleObjectSignatureOfText extends SimpleObjectSignature {

    /**
     * Instantiates a new Object signature of.
     * @param name The Text to be encapsulated.
     * @param type The ObjectType to be encapsulated.
     */
    private SimpleObjectSignatureOfText(final ObjectName name, final ObjectType<?> type) {
        super(name, type);
    }

    /**
     * Instantiates a new Simple object signature of text.
     * @param text The {@link Text} to be encapsulated.
     */
    public SimpleObjectSignatureOfText(final Text text) {
        this(
            new ObjectNameOfScalar(() -> new SimpleObjectSignatureFormat().name(text)),
            new ObjectTypeOfScalar<>(() -> new SimpleObjectSignatureFormat().type(text))
        );
    }

}
