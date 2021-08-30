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

import org.fusionsoft.database.DbObject;
import org.fusionsoft.database.DiffPair;
import org.fusionsoft.database.diffpair.Of;

/**
 * The type Db objects has same dbms signature.
 * @see org.fusionsoft.database.DbmsSignature
 * @since 0.1
 */
public class DbObjectsHasSameDbmsSignature extends ConditionEnvelope {

    /**
     * Instantiates a new Condition that DbObjects has same DbmsSignature.
     * @param pair The pair of objects.
     */
    public DbObjectsHasSameDbmsSignature(final DiffPair<DbObject> pair) {
        super(
            () -> {
                return pair.currentValue().signature().dbmsSignature().equals(
                    pair.previousValue().signature().dbmsSignature()
                );
            }
        );
    }

    /**
     * Instantiates a new Condition that DbObjects has same DbmsSignature.
     * @param first The first DbObject instance.
     * @param second The second DbObject instance.
     */
    public DbObjectsHasSameDbmsSignature(final DbObject first, final DbObject second) {
        this(
            new Of<>(first, second)
        );
    }

}
