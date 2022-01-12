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
package ru.fusionsoft.database.snapshot.dbms;

import org.cactoos.scalar.NumberOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsNumber;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsFromPgMvnDProps;
import ru.fusionsoft.database.connection.ConnectionOfDbdServerMapping;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;

/**
 * The tests for {@link DbmsVersionOfConnection}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines)
 */
class DbmsVersionOfConnectionTest {

    /**
     * Is correct number.
     */
    @Test
    public void isCorrectNumber() {
        new Assertion<>(
            "Version number of Postgres 11 database should be '11'",
            new DbmsVersionOfConnection(
                new ConnectionOfDbdServerMapping(
                    new DbdServerMappingWithCredentials(
                        new UrlOfPgGitLabDatabaseV11(""),
                        new CredsFromPgMvnDProps()
                    )
                )
            ),
            new IsNumber(new NumberOf("11.1"))
        );
    }

}
