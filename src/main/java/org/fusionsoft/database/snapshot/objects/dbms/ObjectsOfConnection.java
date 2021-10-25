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
package org.fusionsoft.database.snapshot.objects.dbms;

import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.scalar.ScalarOf;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectsEnvelope;
import org.fusionsoft.database.snapshot.objects.ObjectsOfScalar;

/**
 * The type of {@link Objects} that can be constructed of {@link Connection}
 *  and function that returns {@link Objects} of {@link Connection}.
 * @since 0.1
 */
public class ObjectsOfConnection extends ObjectsEnvelope {

    /**
     * Instantiates a new Objects of connection.
     * @param connection The Connection to be encapsulated.
     * @param func The Func of Connection returns Objects to be encapsulated.
     */
    public ObjectsOfConnection(final Connection connection, final Func<Connection, Objects> func) {
        super(
            new ObjectsOfScalar(new ScalarOf<>(func, connection))
        );
    }

}
