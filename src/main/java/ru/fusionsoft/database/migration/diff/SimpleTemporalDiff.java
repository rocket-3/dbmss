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
package ru.fusionsoft.database.migration.diff;

/**
 * The simple {@link TemporalDiff} implementation.
 * @param <V> The type parameter.
 * @since 0.1
 */
public class SimpleTemporalDiff<V> implements TemporalDiff<V> {

    /**
     * The current value encapsulated.
     */
    private final V cur;

    /**
     * The next value encapsulated.
     */
    private final V nex;

    /**
     * Instantiates a new Simple temporal diff.
     * @param cur The current {@link V} to be encapsulated.
     * @param nex The current {@link V} to be encapsulated.
     */
    public SimpleTemporalDiff(final V cur, final V nex) {
        this.cur = cur;
        this.nex = nex;
    }

    @Override
    public final V current() {
        return this.cur;
    }

    @Override
    public final V next() {
        return this.nex;
    }

}
