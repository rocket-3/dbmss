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
package ru.fusionsoft.database.ci;

import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfScalar;
import org.cactoos.text.UncheckedText;

/**
 * The {@link org.cactoos.Text} of url of pg v.11 test instance.
 * @since 0.1
 */
public class UrlOfPgGitLabDatabaseV11 extends TextEnvelope {

    /**
     * Instantiates a new Url of pg test database v.11.
     * @param database The String of database name to be encapsulated.
     */
    public UrlOfPgGitLabDatabaseV11(final String database) {
        super(
            new TextOfScalar(
                () -> MessageFormat.format(
                    "jdbc:postgresql://135.181.94.98:31107/{0}",
                    database
                )
            )
        );
    }

    /**
     * Instantiates a new Url of pg test database v.11.
     * @param database The String of database name to be encapsulated.
     */
    public UrlOfPgGitLabDatabaseV11(final Text database) {
        this(
            new UncheckedText(database).asString()
        );
    }

}
