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
package ru.fusionsoft.database.snapshot.objects.ofdbms;

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.scalar.ScalarOf;
import ru.fusionsoft.database.snapshot.Objects;
import ru.fusionsoft.database.snapshot.objects.ObjectsOfScalar;

/**
 * The type of {@link Objects} that can be constructed of {@link Connection}
 *  and function that returns {@link Objects} of {@link Connection}.
 * @param <T> The subtype of {@link YamlNode} parameter.
 * @since 0.1
 */
public class ObjectsOfConnection<T extends YamlNode> extends ObjectsOfScalar<T> {

    /**
     * Instantiates a new Objects of connection.
     * @param connection The Connection to be encapsulated.
     * @param func The Func of Connection returns Objects to be encapsulated.
     */
    public ObjectsOfConnection(
        final Connection connection,
        final Func<Connection, Objects<T>> func
    ) {
        super(
            new ScalarOf<>(
                func,
                connection
            )
        );
    }

}
