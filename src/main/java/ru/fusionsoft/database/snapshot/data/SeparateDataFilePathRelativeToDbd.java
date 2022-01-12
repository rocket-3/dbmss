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

import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;

/**
 * The {@link Text} of separate data file name as path, relative to DBD document path.
 * @since 0.1
 */
public class SeparateDataFilePathRelativeToDbd extends TextEnvelope {

    /**
     * Instantiates a new Separate data file path relative to dbd.
     * @param name The {@link SeparateDataFileName} to be encapsulated.
     */
    public SeparateDataFilePathRelativeToDbd(final SeparateDataFileName name) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format("./{0}", name.asString())
            )
        );
    }

}
