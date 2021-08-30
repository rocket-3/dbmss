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
package org.fusionsoft.database.dbobject;

import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbmsSignature;
import org.fusionsoft.database.description.dbd.DBD;
import org.fusionsoft.lib.exception.NotImplemented;

/**
 * The type Db objects from dbd.
 * @see DbObject
 * @since 0.1
 */
public class DbObjectsFromDbd extends CollectionEnvelope<DbObject> {

    /**
     * Instantiates a collection of DbObjects, extracting from DBD.
     * @param dbd The original DBD.
     * @param signature The dbms signature of extracted objects.
     */
    public DbObjectsFromDbd(final DBD dbd, final DbmsSignature signature) {
        super(
            new ListOf<>(
                new IterableOf<>(
                    () -> {
                        dbd.notifyAll();
                        signature.notifyAll();
                        throw new NotImplemented();
                    }
                )
            )
        );
    }

}
