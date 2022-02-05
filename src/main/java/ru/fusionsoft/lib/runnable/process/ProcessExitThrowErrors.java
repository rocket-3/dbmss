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
package ru.fusionsoft.lib.runnable.process;

import org.cactoos.Output;
import org.cactoos.Text;
import ru.fusionsoft.lib.exception.ProcessRunningException;

/**
 * The {@link ProcessExitHandle} that throws {@link ProcessRunningException}, if exit code is not 0.
 * @since 0.1
 */
public class ProcessExitThrowErrors implements ProcessExitHandle {

    @Override
    public final void handle(
        final int code,
        final Text errors,
        final Output output
    ) throws ProcessRunningException {
        if (code != 0) {
            throw new ProcessRunningException(code, errors);
        }
    }

}
