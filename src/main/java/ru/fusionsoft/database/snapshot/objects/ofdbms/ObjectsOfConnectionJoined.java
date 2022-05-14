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
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ObjectsJoined;

/**
 * The type of {@link Iterable} of {@link DbObject}s that can be constructed of {@link Connection}
 *  and set of functions of {@link Connection} returns {@link Iterable} of {@link DbObject}s.
 * @since 0.1
 */
public class ObjectsOfConnectionJoined extends ObjectsJoined {

    /**
     * Instantiates a new Objects of connection joined.
     * @param connection The Connection to be encapsulated.
     * @param funcs The collection of {@link Connection} to {@link Iterable} of {@link DbObject}'s
     *  funcs to be encapsulated.
     */
    public ObjectsOfConnectionJoined(
        final Connection connection,
        final Iterable<Func<Connection, Iterable<? extends DbObject<? extends YamlNode>>>> funcs
    ) {
        super(
            new Mapped<>(
                x -> x.apply(connection),
                funcs
            )
        );
    }

    /**
     * Instantiates a new Objects of connection joined.
     * @param connection The Connection to be encapsulated.
     * @param funcs The Funcs of Connection return Objects to be encapsulated.
     */
    @SafeVarargs
    public ObjectsOfConnectionJoined(
        final Connection connection,
        final Func<Connection, Iterable<? extends DbObject<? extends YamlNode>>>... funcs
    ) {
        this(connection, new IterableOf<>(funcs));
    }

}
