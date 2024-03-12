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
package ru.fusionsoft.lib.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The {@link Map}, using {@link Scalar} returning values as Map delegate.
 * @param <K> Key type parameter
 * @param <V> Value type parameter
 * @checkstyle LineLengthCheck (200 lines).
 * @checkstyle ParameterNameCheck (200 lines).
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class MapOfScalar<K, V> implements Map<K, V> {

    /**
     * The Scalar.
     */
    private final Unchecked<Map<K, V>> scalar;

    /**
     * Instantiates a new Map of scalar.
     *
     * @param scalar The scalar
     */
    public MapOfScalar(final Scalar<Map<K, V>> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public final int size() {
        return this.scalar.value().size();
    }

    @Override
    public final boolean isEmpty() {
        return this.scalar.value().isEmpty();
    }

    @Override
    public final boolean containsKey(final Object key) {
        return this.scalar.value().containsKey(key);
    }

    @Override
    public final boolean containsValue(final Object value) {
        return this.scalar.value().containsValue(value);
    }

    @Override
    public final V get(final Object key) {
        return this.scalar.value().get(key);
    }

    @Override
    public final V put(final K key, final V value) {
        return this.scalar.value().put(key, value);
    }

    @Override
    public final V remove(final Object key) {
        return this.scalar.value().remove(key);
    }

    @Override
    public final void putAll(final Map<? extends K, ? extends V> m) {
        this.scalar.value().putAll(m);
    }

    @Override
    public final void clear() {
        this.scalar.value().clear();
    }

    @Override
    public final Set<K> keySet() {
        return this.scalar.value().keySet();
    }

    @Override
    public final Collection<V> values() {
        return this.scalar.value().values();
    }

    @Override
    public final Set<Entry<K, V>> entrySet() {
        return this.scalar.value().entrySet();
    }

    @Override
    public final V getOrDefault(final Object key, final V defaultValue) {
        return this.scalar.value().getOrDefault(key, defaultValue);
    }

    @Override
    public final void forEach(final BiConsumer<? super K, ? super V> action) {
        this.scalar.value().forEach(action);
    }

    @Override
    public final void replaceAll(final BiFunction<? super K, ? super V, ? extends V> function) {
        this.scalar.value().replaceAll(function);
    }

    @Override
    public final V putIfAbsent(final K key, final V value) {
        return this.scalar.value().putIfAbsent(key, value);
    }

    @Override
    public final boolean remove(final Object key, final Object value) {
        return this.scalar.value().remove(key, value);
    }

    @Override
    public final boolean replace(final K key, final V oldValue, final V newValue) {
        return this.scalar.value().replace(key, oldValue, newValue);
    }

    @Override
    public final V replace(final K key, final V value) {
        return this.scalar.value().replace(key, value);
    }

    @Override
    public final V computeIfAbsent(final K key, final Function<? super K, ? extends V> mappingFunction) {
        return this.scalar.value().computeIfAbsent(key, mappingFunction);
    }

    @Override
    public final V computeIfPresent(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.scalar.value().computeIfPresent(key, remappingFunction);
    }

    @Override
    public final V compute(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.scalar.value().compute(key, remappingFunction);
    }

    @Override
    public final V merge(final K key, final V value, final BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return this.scalar.value().merge(key, value, remappingFunction);
    }

    @Override
    public final int hashCode() {
        return this.scalar.value().hashCode();
    }

    @Override
    public final boolean equals(final Object o) {
        return this.scalar.value().equals(o);
    }

}
