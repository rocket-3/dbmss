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
package ru.fusionsoft.database.dbdfile;

import com.amihaiemil.eoyaml.YamlMapping;
import ru.fusionsoft.database.DbdFile;
import ru.fusionsoft.database.mapping.dbd.DbdRootMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdRootMappingMerged;

/**
 * The {@link DbdFile} of two merged {@link DbdFile}s.
 * @since 0.1
 */
public class DbdFileMerged extends DbdFileOfRootMapping {

    /**
     * Instantiates a new Dbd file merged.
     * @param original The {@link DbdFile} to be encapsulated.
     * @param target The {@link DbdFile} to be encapsulated.
     */
    public DbdFileMerged(final DbdFile original, final DbdFile target) {
        super(
            new DbdRootMappingMerged(
                new DbdRootMapping(original::asYaml),
                new DbdRootMapping(target::asYaml)
            )
        );
    }

    /**
     * Instantiates a new Dbd file merged.
     * @param original The {@link DbdFile} to be encapsulated.
     * @param target The {@link DbdFile} to be encapsulated.
     */
    public DbdFileMerged(final DbdFile original, final YamlMapping target) {
        super(
            new DbdRootMappingMerged(
                new DbdRootMapping(original::asYaml),
                target
            )
        );
    }

}
