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
package org.fusionsoft.database.writable;

import org.cactoos.text.TextOf;
import org.fusionsoft.database.Writable;
import org.fusionsoft.database.snapshot.AstronomicalTime;
import org.fusionsoft.lib.yaml.MappingEmpty;

/**
 * The type {@link Writable} that represents db objects snapshot info data.
 * @since 0.1
 * @checkstyle ParameterNumberCheck (100 lines)
 * @checkstyle ParameterNameCheck (100 lines)
 * @todo #40:60min Implement `SnapshotInfo` `Writable`.
 */
@SuppressWarnings("PMD")
public class SnapshotInfoDocument extends WritableYamlDocument {

    /**
     * Instantiates a new Snapshot info.
     * @param time The AstronomicalTime to be encapsulated.
     * @param withOperationalData Was the snapshot with operational data
     *  or configuration data only.
     */
    public SnapshotInfoDocument(
        final AstronomicalTime time,
        final Boolean withOperationalData
    ) {
        super(new MappingEmpty(), new TextOf(".snapshot.yml"));
    }

}
