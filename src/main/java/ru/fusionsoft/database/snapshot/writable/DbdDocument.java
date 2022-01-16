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

import org.cactoos.text.TextOf;
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.dbdfile.DbdFileOfMapping;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.writable.YamlDocument;

public class DbdDocument extends YamlDocument {

    public DbdDocument(final DbdFile file) {
        super(
            file,
            new TextOf("DBD.yaml")
        );
    }

    public DbdDocument(final DbdRootMapping mapping) {
        this(
            new DbdFileOfMapping(mapping)
        );
    }

}
