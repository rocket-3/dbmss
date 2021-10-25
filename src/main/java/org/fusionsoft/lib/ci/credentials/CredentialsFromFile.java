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
package org.fusionsoft.lib.ci.credentials;

import java.nio.file.Path;
import java.util.List;
import org.cactoos.Text;
import org.cactoos.list.ListOf;
import org.cactoos.text.Split;
import org.cactoos.text.TextOf;

/**
 * The type of {@link Credentials} that can be constructed of file.
 * @since 0.1
 */
public class CredentialsFromFile extends CredentialsOfScalar {

    /**
     * Instantiates a new Credentials from file.
     * @param lines The List of Text to be encapsulated.
     */
    private CredentialsFromFile(final List<Text> lines) {
        super(() -> new SimpleCredentials(
            lines.get(0).asString().trim(),
            lines.get(1).asString().trim()
        ));
    }

    /**
     * Instantiates a new Credentials from file.
     * @param path The Path to be encapsulated.
     */
    public CredentialsFromFile(final Path path) {
        this(
            new ListOf<Text>(
                new Split(
                    new TextOf(path),
                    "\n"
                )
            )
        );
    }

}
