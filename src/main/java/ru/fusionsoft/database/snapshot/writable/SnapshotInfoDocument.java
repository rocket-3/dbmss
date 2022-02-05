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
package ru.fusionsoft.database.snapshot.writable;

import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfScalar;
import ru.fusionsoft.database.mapping.entries.ScalarEntry;
import ru.fusionsoft.lib.path.Writable;
import ru.fusionsoft.lib.path.writable.YamlDocument;
import ru.fusionsoft.lib.text.TextOfUtcDate;
import ru.fusionsoft.lib.time.Utc;
import ru.fusionsoft.lib.yaml.SimpleYamlRepresentative;
import ru.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type {@link Writable} that represents db objects snapshot info data.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 * @checkstyle ParameterNameCheck (100 lines)
 * @todo #40:60min Implement `SnapshotInfo` `Writable`.
 */
@SuppressWarnings("PMD")
public class SnapshotInfoDocument extends YamlDocument {

    /**
     * Instantiates a new Snapshot info.
     * @param time The AstronomicalTime to be encapsulated.
     * @param withOperationalData Was the snapshot with operational data
     *  or configuration data only.
     */
    public SnapshotInfoDocument(
        final Utc time,
        final Boolean withOperationalData
    ) {
        super(
            new SimpleYamlRepresentative<>(
                new YamlMappingOfEntries(
                    new ScalarEntry(
                        new TextOf("createdAt"),
                        new TextOfUtcDate(time)
                    ),
                    new ScalarEntry(
                        new TextOf("withOperationalData"),
                        new TextOfScalar(() -> String.valueOf(withOperationalData))
                    )
                )
            ),
            new TextOf(".snapshot.yml")
        );
    }

}
