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

import org.cactoos.iterable.IterableEnvelope;

/**
 * The {@link Iterable} with {@link AutoCloseable} behaviour injected.
 * @param <T> The type parameter.
 * @since 0.1
 */
public class IterableAutoCloseable<T> extends IterableEnvelope<T> implements AutoCloseable {

    /**
     * The {@link AutoCloseable} encapsulated.
     */
    private final AutoCloseable closeable;

    /**
     * Instantiates a new Iterable auto closeable.
     * @param iterable The {@link Iterable} to be encapsulated.
     * @param closeable The {@link AutoCloseable} to be encapsulated.
     */
    public IterableAutoCloseable(final Iterable<T> iterable, final AutoCloseable closeable) {
        super(iterable);
        this.closeable = closeable;
    }

    /**
     * Instantiates a new Iterable auto closeable.
     * @param iterable The {@link Iterable} to be encapsulated.
     * @param closeables The {@link AutoCloseable} array to be encapsulated.
     */
    public IterableAutoCloseable(final Iterable<T> iterable, final AutoCloseable... closeables) {
        this(iterable, new JoinedAutoCloseable(closeables));
    }

    @Override
    public void close() throws Exception {
        this.closeable.close();
    }

}
