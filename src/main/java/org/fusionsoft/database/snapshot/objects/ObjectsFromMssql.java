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

import java.sql.Connection;
import org.cactoos.iterable.IterableOf;

/**
 * The Objects of {@link Connection} of MS SQL Server dbms.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ObjectsFromMssql extends ObjectsEnvelope {

    /**
     * Ctor.
     * @param connection The wrapped connection
     */
    public ObjectsFromMssql(final Connection connection) {
        super(new IterableOf<>());
    }

}
