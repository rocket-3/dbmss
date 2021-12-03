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

package org.fusionsoft.lib.collection;

import org.cactoos.iterable.IterableOf;

public class JoinedAutoCloseable implements AutoCloseable {

    private final Iterable<AutoCloseable> iterable;

    public JoinedAutoCloseable(final Iterable<AutoCloseable> iterable) {
        this.iterable = iterable;
    }

    public JoinedAutoCloseable(final AutoCloseable... values) {
        this(new IterableOf<>(values));
    }

    @Override
    public void close() throws Exception {
        for (final AutoCloseable closeable : this.iterable) {
            closeable.close();
        }
    }

}
