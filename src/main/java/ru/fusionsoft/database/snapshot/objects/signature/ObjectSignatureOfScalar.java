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

public class ObjectSignatureOfScalar implements ObjectSignature {

    private final Unchecked<ObjectSignature> scalar;

    public ObjectSignatureOfScalar(final Scalar<ObjectSignature> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public ObjectName name() {
        return this.scalar.value().name();
    }

    @Override
    public ObjectType<?> type() {
        return this.scalar.value().type();
    }

    @Override
    public String asString() {
        return this.scalar.value().asString();
    }

    @Override
    public boolean equalsTo(final ObjectSignature other) {
        return this.scalar.value().equalsTo(other);
    }

}
