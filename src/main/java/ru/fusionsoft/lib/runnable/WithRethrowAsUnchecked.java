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

import org.cactoos.Proc;
import ru.fusionsoft.lib.exception.UncheckedOfThrowable;

/**
 * The process of running any {@link RunnableWithException} and wrapping any {@link Exception} as
 *  {@link UncheckedOfThrowable}.
 * @since 0.1
 * @checkstyle IllegalCatchCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidCatchingGenericException")
public class WithRethrowAsUnchecked implements Proc<RunnableWithException<? extends Exception>> {

    @Override
    public final void exec(final RunnableWithException<? extends Exception> execution) {
        try {
            execution.run();
        } catch (final Exception throwable) {
            throw new UncheckedOfThrowable(throwable);
        }
    }

}
