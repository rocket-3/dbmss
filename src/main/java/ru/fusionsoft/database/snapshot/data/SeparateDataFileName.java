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
package ru.fusionsoft.database.snapshot.data;

import org.cactoos.text.Joined;
import org.cactoos.text.TextEnvelope;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.lib.yaml.FileExtension;

/**
 * The name of separate data file name {@link org.cactoos.Text}.
 * @since 0.1
 */
public class SeparateDataFileName extends TextEnvelope {

    /**
     * Instantiates a new Separate data file name.
     * @param table The {@link DbObject} of {@link DbdTableMapping} to be encapsulated.
     */
    public SeparateDataFileName(final DbObject<DbdTableMapping> table) {
        super(
            new Joined(
                ".",
                table.signature().name().parent().first(),
                table.signature().name().first(),
                new SeparateDataFileNameSuffix(),
                new FileExtension()
            )
        );
    }

}
