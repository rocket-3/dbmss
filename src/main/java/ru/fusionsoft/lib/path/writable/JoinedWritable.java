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
package ru.fusionsoft.lib.path.writable;

import org.cactoos.iterable.IterableOf;
import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.Writable;

/**
 * The {@link Writable} that represents an iterable of writeables.
 * @since 0.1
 */
public class JoinedWritable implements Writable {

    /**
     * The Iterable of Writable encapsulated.
     */
    private final Iterable<Writable> writables;

    /**
     * Instantiates a new Combined writable.
     * @param writeables The Iterable of Writable to be encapsulated.
     */
    public JoinedWritable(final Iterable<Writable> writeables) {
        this.writables = writeables;
    }

    /**
     * Instantiates a new Combined writable.
     * @param writeables The Iterable of Writable to be encapsulated.
     */
    public JoinedWritable(final Writable... writeables) {
        this(new IterableOf<Writable>(writeables));
    }

    @Override
    public final void writeTo(final Directory directory) {
        for (final Writable writable : this.writables) {
            writable.writeTo(directory);
        }
    }

}
