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
package org.fusionsoft.database.condition;

import java.util.Collection;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DbmsSignature;

/**
 * The Condition that each db object has same dbms signature.
 * @since 0.1
 */
public class EachDboHasDbmsSignature extends ConditionEnvelope {

    /**
     * Instantiates a new Condition that each db object has same dbms signature.
     * @param signature The dbms signature.
     * @param objects The db object collection.
     */
    public EachDboHasDbmsSignature(
        final DbmsSignature signature,
        final Collection<DbObject> objects
    ) {
        super(
            new And(
                new Mapped<>(
                    x -> new DboHasDbmsSignature(signature, x),
                    objects
                )
            )
        );
    }

}
