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
package org.fusionsoft.database.dbdfile;

import org.cactoos.Scalar;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.mapping.dbd.DbdRootMapping;

/**
 * The type of {@link DbdFile} can be constructed of {@link DbdRootMapping}.
 * @since 0.1
 */
public class DbdFileOfRootMapping implements DbdFile {

    /**
     * The Scalar of DbdRootMapping encapsulated.
     */
    private final Scalar<DbdRootMapping> mapping;

    /**
     * Instantiates a new Dbd file of dbd root mapping.
     * @param mapping The Scalar of DbdRootMapping to be encapsulated.
     */
    public DbdFileOfRootMapping(final Scalar<DbdRootMapping> mapping) {
        this.mapping = mapping;
    }

    /**
     * Instantiates a new Dbd file of dbd root mapping.
     * @param mapping The DbdRootMapping to be encapsulated.
     */
    public DbdFileOfRootMapping(final DbdRootMapping mapping) {
        this(() -> mapping);
    }

    @Override
    public final DbdRootMapping asYaml() {
        return new Unchecked<>(this.mapping).value();
    }

}
