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
package ru.fusionsoft.lib.runnable;

import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;

/**
 * The Runnable, that you can construct of {@link Scalar} of Runnable...
 * @since 0.1
 */
public class RunnableOfScalar implements Runnable {

    /**
     * The Scalar encapsulated.
     */
    private final Scalar<Runnable> runnable;

    /**
     * Instantiates a new Runnable of scalar.
     * @param runnable The {@link Scalar} to be encapsulated.
     */
    public RunnableOfScalar(final Scalar<Runnable> runnable) {
        this.runnable = runnable;
    }

    @Override
    public final void run() {
        new Unchecked<>(this.runnable).value().run();
    }

}
