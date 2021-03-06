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
package ru.fusionsoft.database.snapshot;

import java.text.MessageFormat;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.lib.text.HexOfLong;
import ru.fusionsoft.lib.time.Utc;

/**
 * The type of {@link org.cactoos.Text} that represents name of snapshot created
 *  at some {@link Utc} moment.
 * @since 0.1
 */
public class SnapshotCatalogName extends TextEnvelope {

    /**
     * Instantiates a new Snapshot name.
     * @param time The AstronomicalTime to be encapsulated.
     */
    public SnapshotCatalogName(final Utc time) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format(
                    "{0} {1}",
                    "Snapshot",
                    new HexOfLong(time::millis).asString()
                )
            )
        );
    }

}
