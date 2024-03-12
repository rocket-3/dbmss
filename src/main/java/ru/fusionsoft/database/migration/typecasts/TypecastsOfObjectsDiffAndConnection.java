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
package ru.fusionsoft.database.migration.typecasts;

import java.sql.Connection;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.Joined;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;

/**
 * The {@link Typecast} made of {@link ObjectsDiff}.
 * @since 0.1
 */
public class TypecastsOfObjectsDiffAndConnection extends IterableEnvelope<Typecast> {

    /**
     * Instantiates a new Typecasts of objects diff and connection.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     */
    public TypecastsOfObjectsDiffAndConnection(
        final ObjectsDiff diff,
        final Connection connection
    ) {
        super(
            new Unchecked<>(
                () -> {
                    return new ListOf<>(
                        new Joined<Typecast>(
                            new UdtTypecastsOfObjectsDiffAndConnection(diff, connection),
                            new TableTypecastsOfObjectsDiffAndConnection(diff, connection)
                        )
                    );
                }
            ).value()
        );
    }

}
