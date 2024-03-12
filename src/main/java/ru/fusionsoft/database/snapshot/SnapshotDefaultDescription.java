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
import org.cactoos.Text;
import ru.fusionsoft.database.mapping.dbd.DbdServerMapping;
import ru.fusionsoft.database.mapping.fields.DbdServerFields;
import ru.fusionsoft.lib.yaml.artefacts.TextOfYamlMappingKeyValue;

/**
 * The default description of bare snapshot {@link Text} representation.
 * @since 0.1
 */
public class SnapshotDefaultDescription implements Text {

    /**
     * The DbdServerMapping encapsulated.
     */
    private final DbdServerMapping mapping;

    /**
     * Instantiates a new Snapshot default description.
     * @param mapping The {@link DbdServerMapping} to be encapsulated.
     */
    public SnapshotDefaultDescription(final DbdServerMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public final String asString() {
        return MessageFormat.format(
            "Snapshot of database at {0}",
            new TextOfYamlMappingKeyValue(
                this.mapping,
                DbdServerFields.URL
            ).asString()
        );
    }

}
