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
package ru.fusionsoft.lib.connection;

import java.sql.DriverManager;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;

/**
 * The {@link ConnectionOfScalar} of url, user and pass {@link Text} arguments.
 * @since 0.1
 */
public class ConnectionOfTextArgs extends ConnectionOfScalar {

    /**
     * Instantiates a new Connection of scalar.
     * @param url The {@link Text} to be encapsulated.
     * @param user The {@link Text} to be encapsulated.
     * @param pass The {@link Text} to be encapsulated.
     */
    public ConnectionOfTextArgs(final Text url, final Text user, final Text pass) {
        super(
            new Sticky<>(
                () -> DriverManager.getConnection(
                    url.asString(),
                    user.asString(),
                    pass.asString()
                )
            )
        );
    }

}
