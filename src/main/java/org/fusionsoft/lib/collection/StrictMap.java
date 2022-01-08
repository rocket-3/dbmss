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
package org.fusionsoft.lib.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.cactoos.text.TextOf;
import org.fusionsoft.lib.exception.TextOfMap;
import org.fusionsoft.lib.exception.ValueNotFoundException;

/**
 * Map wrapper, allowing no nulls to be returned.
 * @param <X> The type of key parameter.
 * @param <Y> The type of value parameter.
 * @see RuntimeException is thrown on `get(X)` method
 *  if the original map doesn't contain the X key.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class StrictMap<X, Y> implements Map<X, Y> {

    /**
     * The Map encapsulated.
     */
    private final Map<X, Y> map;

    /**
     * Instantiates a new Strict map.
     * @param original The original map.
     */
    public StrictMap(final Map<X, Y> original) {
        this.map = original;
    }

    @Override
    public final int size() {
        return this.map.size();
    }

    @Override
    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public final boolean containsKey(final Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public final boolean containsValue(final Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public final Y get(final Object key) {
        if (this.containsKey(key)) {
            return this.map.get(key);
        } else {
            throw new ValueNotFoundException(
                key.toString(),
                new TextOfMap(this).asString()
            );
        }
    }

    @Override
    public final Y put(final X key, final Y value) {
        return this.map.put(key, value);
    }

    @Override
    public final Y remove(final Object key) {
        return this.map.remove(key);
    }

    @Override
    public final void putAll(final Map<? extends X, ? extends Y> extra) {
        this.map.putAll(extra);
    }

    @Override
    public final void clear() {
        this.map.clear();
    }

    @Override
    public final Set<X> keySet() {
        return this.map.keySet();
    }

    @Override
    public final Collection<Y> values() {
        return this.map.values();
    }

    @Override
    public final Set<Map.Entry<X, Y>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public final String toString() {
        return new StringBuilder()
            .append('{')
            .append(new TextOf(this.entrySet()).toString())
            .append('}')
            .toString();
    }

    @Override
    public final boolean equals(final Object other) {
        return this.map.equals(other);
    }

    @Override
    public final int hashCode() {
        return this.map.hashCode();
    }

}
