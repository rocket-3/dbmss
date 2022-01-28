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
package ru.fusionsoft.database;

/**
 * The {@link Runnable} that writes {@link Writable} to {@link Folder}.
 * @since 0.1
 */
public class WriteToFolderProcedure implements Runnable {

    /**
     * The Writable encapsulated.
     */
    private final Writable writable;

    /**
     * The Folder encapsulated.
     */
    private final Folder folder;

    /**
     * Instantiates a new Write to folder procedure.
     * @param writable The {@link Writable} to be encapsulated.
     * @param folder The {@link Folder} to be encapsulated.
     */
    public WriteToFolderProcedure(final Writable writable, final Folder folder) {
        this.writable = writable;
        this.folder = folder;
    }

    @Override
    public final void run() {
        this.writable.writeTo(this.folder);
    }

}
