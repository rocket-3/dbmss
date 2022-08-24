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

package ru.fusionsoft.database.migration;

import org.cactoos.Text;
import org.cactoos.text.TextOf;

/**
 * A {@link Migration}, assembled from its behaviour parts.
 * @since 0.1
 */
public class SimpleMigration implements Migration {

    /**
     * The Text of description encapsulated.
     */
    private final Text desc;

    /**
     * The Text of sql encapsulated.
     */
    private final Text code;

    /**
     * Instantiates a new Simple migration.
     * @param desc The {@link Text} to be encapsulated.
     * @param code The {@link Text} to be encapsulated.
     */
    public SimpleMigration(final Text desc, final Text code) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * Instantiates a new Simple migration.
     * @param desc The {@link String} to be encapsulated.
     * @param code The {@link String} to be encapsulated.
     */
    public SimpleMigration(final String desc, final String code) {
        this(
            new TextOf(desc),
            new TextOf(code)
        );
    }

    @Override
    public final Text description() {
        return this.desc;
    }

    @Override
    public final Text sql() {
        return this.code;
    }

}
