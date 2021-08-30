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
package org.fusionsoft.database;

import java.nio.file.Path;
import org.fusionsoft.database.is.FromYamlInput;
import org.fusionsoft.lib.functional.RunnableWithException;

/**
 * Root thing, very close to CMDRestore.
 * @since 0.1
 */
public class RestoreProcedure implements RunnableWithException {

    /**
     * The IS encapsulated.
     */
    private final IS<?> state;

    /**
     * Instantiates a new Restore procedure.
     * @param path The Path to be encapsulated.
     * @param server The key from 'server' section to get target DBMS from.
     * @param params The RestoreParams to be encapsulated.
     */
    public RestoreProcedure(
        final Path path,
        final CharSequence server,
        final RestoreParams params
    ) {
        this.state = new FromYamlInput(
            path,
            server,
            params
        );
    }

    @Override
    public final void run() throws Exception {
        this.state.restore().perform();
    }

}
