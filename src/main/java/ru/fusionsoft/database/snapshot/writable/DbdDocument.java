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
package ru.fusionsoft.database.snapshot.writable;

import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.dbdfile.DbdFileOfMapping;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.text.DbdFileName;
import ru.fusionsoft.database.writable.YamlDocument;

/**
 * The {@link ru.fusionsoft.database.Writable} of {@link DbdFile} to {@link DbdFileName}.
 * @since 0.1
 */
public class DbdDocument extends YamlDocument {

    /**
     * Instantiates a new Dbd document.
     * @param file The {@link DbdFile} to be encapsulated.
     */
    public DbdDocument(final DbdFile file) {
        super(
            file,
            new DbdFileName()
        );
    }

    /**
     * Instantiates a new Dbd document.
     * @param mapping The {@link DbdRootMapping} to be encapsulated.
     */
    public DbdDocument(final DbdRootMapping mapping) {
        this(
            new DbdFileOfMapping(mapping)
        );
    }

}
