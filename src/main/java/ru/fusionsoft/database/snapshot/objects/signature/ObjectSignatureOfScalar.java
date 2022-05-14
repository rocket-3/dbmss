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

import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.snapshot.ObjectSignature;

/**
 * The {@link ObjectSignature} of {@link Scalar}.
 * @since 0.1
 */
public class ObjectSignatureOfScalar implements ObjectSignature {

    /**
     * The scalar encapsulated.
     */
    private final Unchecked<ObjectSignature> scalar;

    /**
     * Instantiates a new Object signature of scalar.
     * @param scalar The {@link Scalar} to be encapsulated.
     */
    public ObjectSignatureOfScalar(final Scalar<ObjectSignature> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final ObjectName name() {
        return this.scalar.value().name();
    }

    @Override
    public final ObjectType<?> type() {
        return this.scalar.value().type();
    }

    @Override
    public final String asString() {
        return this.scalar.value().asString();
    }

    @Override
    public final boolean equalsTo(final ObjectSignature other) {
        return this.scalar.value().equalsTo(other);
    }

}
