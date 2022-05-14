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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.Sticky;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;
import ru.fusionsoft.database.ci.UrlOfPgGitLabDatabaseV11;
import ru.fusionsoft.database.ci.credentials.CredsOfPgTestDatabase;
import ru.fusionsoft.database.mapping.dbd.built.DbdServerMappingWithCredentials;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithTypeAndNameMatchesRegexp;
import ru.fusionsoft.database.snapshot.objects.ofdbms.ObjectsOfServer;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;

/**
 * The test for {@link ObjectsOfOneAreDependenciesOfAnother}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 */
@SuppressWarnings("PMD")
class ObjectsOfOneWhichAnotherDependsOnTest {

    /**
     * Locates expected tables.
     */
    @Test
    public void locatesExpectedTables() {
        final Iterable<DbObject<YamlNode>> all = new Sticky<>(
            new ObjectsOfServer(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11("pagilla"),
                    new CredsOfPgTestDatabase()
                )
            )
        );
        final Iterable<DbObject<YamlNode>> updated = new Sticky<>(
            new ObjectsWithTypeAndNameMatchesRegexp<YamlNode>(
                new TextOf("table"),
                new TextOf(".*rental"),
                all
            )
        );
        final Iterable<DbObject<YamlNode>> deps = new Sticky<>(
            new ObjectsOfOneAreDependenciesOfAnother<>(
                all,
                updated
            )
        );
        new Assertion<>(
            "Should match expected tables",
            new Mapped<ObjectSignature>(
                DbObject::signature,
                deps
            ),
            new HasValues<>(
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "staff"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "customer"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "inventory"),
                    new ObjectTypeTable()
                )
            )
        ).affirm();
    }

}
