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
package org.fusionsoft.database.snapshot.objects.signature.name;

import java.util.Objects;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;

/**
 * The {@link ObjectName}, that uses {@link Scalar} of {@link ObjectName} as delegate.
 * @since 0.1
 */
public class ObjectNameOfScalar implements ObjectName {

    /**
     * The scalar of {@link ObjectName} encapsulated.
     */
    private final Unchecked<ObjectName> scalar;

    /**
     * Instantiates a new Object name of scalar.
     * @param scalar The {@link Scalar} of {@link ObjectName} to be encapsulated.
     */
    public ObjectNameOfScalar(final Scalar<ObjectName> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final String asString() {
        return this.scalar.value().asString();
    }

    @Override
    public final ObjectName parent() {
        return this.scalar.value().parent();
    }

    @Override
    public final Text first() {
        return this.scalar.value().first();
    }

    @Override
    public final boolean equalsTo(final ObjectName other) {
        return this.scalar.value().equalsTo(other);
    }

    @Override
    public final boolean equals(final Object obj) {
        boolean equ = false;
        if (obj instanceof ObjectName) {
            equ = this.equalsTo((ObjectName) obj);
        }
        return equ;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.asString());
    }

}
