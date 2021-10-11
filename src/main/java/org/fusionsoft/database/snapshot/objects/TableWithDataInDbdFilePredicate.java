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
package org.fusionsoft.database.snapshot.objects;

import org.cactoos.Func;
import org.fusionsoft.database.DbdFile;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The predicate of DbObject to test it's a configuration table in DBD.
 * @since 0.1
 * @todo #40:30min Implement 'apply' method.
 */
public class TableWithDataInDbdFilePredicate implements Func<DbObject<?>, Boolean> {

    /**
     * The DbdFile encapsulated.
     */
    private final DbdFile file;

    /**
     * Instantiates a new Object is dbd configuration table predicate.
     * @param file The DbdFile to be encapsulated.
     */
    public TableWithDataInDbdFilePredicate(final DbdFile file) {
        this.file = file;
    }

    @Override
    public final Boolean apply(final DbObject input) {
        this.file.notifyAll();
        throw new NotImplemented();
    }

}
