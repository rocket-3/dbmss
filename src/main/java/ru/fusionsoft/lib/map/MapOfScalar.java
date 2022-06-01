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

public class MapOfScalar<K, V> implements Map<K, V> {

    private final Unchecked<Map<K, V>> scalar;

    public MapOfScalar(final Scalar<Map<K, V>> scalar) {
        this.scalar = new Unchecked<>(scalar);
    }

    @Override
    public int size() {
        return scalar.value().size();
    }

    @Override
    public boolean isEmpty() {
        return scalar.value().isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return scalar.value().containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return scalar.value().containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return scalar.value().get(key);
    }

    @Override
    public V put(final K key, final V value) {
        return scalar.value().put(key, value);
    }

    @Override
    public V remove(final Object key) {
        return scalar.value().remove(key);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> m) {
        scalar.value().putAll(m);
    }

    @Override
    public void clear() {
        scalar.value().clear();
    }

    @Override
    public Set<K> keySet() {
        return scalar.value().keySet();
    }

    @Override
    public Collection<V> values() {
        return scalar.value().values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return scalar.value().entrySet();
    }

    @Override
    public boolean equals(final Object o) {
        return scalar.value().equals(o);
    }

    @Override
    public int hashCode() {
        return scalar.value().hashCode();
    }

    @Override
    public V getOrDefault(final Object key, final V defaultValue) {
        return scalar.value().getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(final BiConsumer<? super K, ? super V> action) {
        scalar.value().forEach(action);
    }

    @Override
    public void replaceAll(final BiFunction<? super K, ? super V, ? extends V> function) {
        scalar.value().replaceAll(function);
    }

    @Override
    public V putIfAbsent(final K key, final V value) {
        return scalar.value().putIfAbsent(key, value);
    }

    @Override
    public boolean remove(final Object key, final Object value) {
        return scalar.value().remove(key, value);
    }

    @Override
    public boolean replace(final K key, final V oldValue, final V newValue) {
        return scalar.value().replace(key, oldValue, newValue);
    }

    @Override
    public V replace(final K key, final V value) {
        return scalar.value().replace(key, value);
    }

    @Override
    public V computeIfAbsent(final K key, final Function<? super K, ? extends V> mappingFunction) {
        return scalar.value().computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return scalar.value().computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(final K key, final BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return scalar.value().compute(key, remappingFunction);
    }

    @Override
    public V merge(final K key, final V value, final BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return scalar.value().merge(key, value, remappingFunction);
    }

}
