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
package org.fusionsoft.database.snapshot.objectsignature;

import org.cactoos.iterable.TailOf;
import org.cactoos.scalar.ItemAt;
import org.cactoos.text.Split;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.lib.text.RegexpPatternLiteralEscaped;

/**
 * The text representing parent name of
 *  {@link org.fusionsoft.database.snapshot.DbObject} from its name
 *  with {@link FullObjectName} origin.
 * @since 0.1
 */
public class ParentName extends TextEnvelope {

    /**
     * Ctor.
     * @param joined Text representing the joined value.
     */
    public ParentName(final FullObjectName joined) {
        super(
            new TextOfScalar(
                () -> new ItemAt<>(
                    0,
                    new TailOf<>(
                        2,
                        new Split(
                            joined,
                            new RegexpPatternLiteralEscaped(
                                new FullNameDelimiter()
                            )
                        )
                    )
                ).value().asString()
            )
        );
    }

}
