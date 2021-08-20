package org.fusionsoft.lib.collection;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.cactoos.text.TextOf;

/**
 * Map wrapper, allowing no nulls to be returned
 * @see RuntimeException is thrown on `get(X)` method if the original map doesn't contain the X key
 */
public class StrictMap<X, Y> implements Map<X, Y>{
    private final Map<X, Y> map;

    /**
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
        if(this.containsKey(key)) {
            return this.map.get(key);
        } else {
            throw new RuntimeException(MessageFormat.format("No value found for key {0}", key.toString()));
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
