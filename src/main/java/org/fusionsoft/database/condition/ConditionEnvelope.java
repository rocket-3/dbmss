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
package org.fusionsoft.database.condition;

import org.cactoos.Scalar;
import org.fusionsoft.database.Condition;

/**
 * The type Condition implementation that delegates everything on wrapped one.
 * @since 0.1
 */
public class ConditionEnvelope implements Condition {

    /**
     * The wrapped condition predecessor.
     */
    private final Scalar<Boolean> origin;

    /**
     * Instantiates a new Condition from a Boolean Scalar.
     * @param origin The scalar of boolean, the delegate.
     * @since 0.1
     */
    public ConditionEnvelope(final Scalar<Boolean> origin) {
        this.origin = origin;
    }

    @Override
    public final Boolean value() throws Exception {
        return this.origin.value();
    }

}
