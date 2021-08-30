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
package org.fusionsoft.database.diffpair;

import org.fusionsoft.database.DiffPair;

/**
 * The naive {@link DiffPair} implementation.
 * @param <Y> The element type parameter.
 * @since 0.1
 */
public class Of<Y> implements DiffPair<Y> {

    /**
     * The first value wrapped.
     */
    private final Y first;

    /**
     * The second value wrapped.
     */
    private final Y second;

    /**
     * Instantiates a new DiffPairOf.
     * @param first The first value.
     * @param second The second value.
     */
    public Of(final Y first, final Y second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public final Y previousValue() {
        return this.first;
    }

    @Override
    public final Y currentValue() {
        return this.second;
    }

}
