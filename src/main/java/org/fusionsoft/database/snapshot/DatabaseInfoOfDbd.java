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
package org.fusionsoft.database.snapshot;

import org.fusionsoft.database.DbdFile;

/**
 * The type of {@link DatabaseInfo} that is obtained from {@link DbdFile} and
 *  name of server for which data is.
 * @since 0.1
 * @todo #40:30min Obtain database info from Dbd file.
 */
@SuppressWarnings("PMD")
public class DatabaseInfoOfDbd implements DatabaseInfo {

    /**
     * The DbdFile encapsulated.
     */
    private final DbdFile file;

    /**
     * The String encapsulated.
     */
    private final String name;

    /**
     * Instantiates a new Database info of dbd.
     * @param file The DbdFile to be encapsulated.
     * @param name The String to be encapsulated.
     */
    public DatabaseInfoOfDbd(final DbdFile file, final String name) {
        this.file = file;
        this.name = name;
    }

}
