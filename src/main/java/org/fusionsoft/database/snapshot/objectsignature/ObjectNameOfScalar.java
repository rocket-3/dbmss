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

package org.fusionsoft.database.snapshot.objectsignature;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;

public class ObjectNameOfScalar implements ObjectName{

    private final Unchecked<ObjectName> scalar;

    public ObjectNameOfScalar(final Scalar<ObjectName> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public String asString() {
        return this.scalar.value().asString();
    }

    @Override
    public ObjectName parent() {
        return this.scalar.value().parent();
    }

    @Override
    public Text first() {
        return this.scalar.value().first();
    }

}
