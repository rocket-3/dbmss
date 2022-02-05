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

import ru.fusionsoft.lib.path.Directory;
import ru.fusionsoft.lib.path.Writable;

/**
 * The {@link Runnable} that writes {@link Writable} to {@link Directory}.
 * @since 0.1
 */
public class WriteToDirectoryRunnable implements Runnable {

    /**
     * The Writable encapsulated.
     */
    private final Writable writable;

    /**
     * The Folder encapsulated.
     */
    private final Directory directory;

    /**
     * Instantiates a new Write to folder procedure.
     * @param writable The {@link Writable} to be encapsulated.
     * @param directory The {@link Directory} to be encapsulated.
     */
    public WriteToDirectoryRunnable(final Writable writable, final Directory directory) {
        this.writable = writable;
        this.directory = directory;
    }

    @Override
    public final void run() {
        this.writable.writeTo(this.directory);
    }

}
