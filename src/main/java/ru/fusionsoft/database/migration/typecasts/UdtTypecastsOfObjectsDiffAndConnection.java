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
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.migration.diff.ObjectsDiff;
import ru.fusionsoft.database.migration.diff.ObjectsOfObjectsDiff;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithUdtOrigin;

/**
 * Collects all {@link Typecast}'s from UDT's diffs of {@link ObjectsDiff}.
 * @since 0.1
 */
public class UdtTypecastsOfObjectsDiffAndConnection extends IterableEnvelope<Typecast> {

    /**
     * Instantiates a new Udt typecasts of objects diff and connection.
     * @param diff The {@link ObjectsDiff} to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     */
    public UdtTypecastsOfObjectsDiffAndConnection(
        final ObjectsDiff diff,
        final Connection connection
    ) {
        super(
            new Unchecked<Iterable<Typecast>>(
                () -> {
                    return new ListOf<>(
                        new Mapped<Typecast>(
                            pair -> new TypecastOfUdtDiffAndConnection(pair, connection),
                            new ObjectsOfObjectsDiff(
                                TemporalDiff::current,
                                ObjectsWithUdtOrigin::new,
                                diff.changed()
                            )
                        )
                    );
                }
            ).value()
        );
    }

}
