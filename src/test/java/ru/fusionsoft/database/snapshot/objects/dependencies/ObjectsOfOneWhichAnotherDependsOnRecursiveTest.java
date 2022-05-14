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
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.iterable.MappedWithIndex;
import org.cactoos.iterable.Sticky;
import org.cactoos.text.Joined;
import org.cactoos.text.Newline;
import org.cactoos.text.Repeated;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Disabled;
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
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The test for {@link ObjectsOfOneAreDependenciesOfAnotherRecursive}.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines)
 * @checkstyle MagicNumberCheck (200 lines)
 */
@SuppressWarnings("PMD")
class ObjectsOfOneWhichAnotherDependsOnRecursiveTest {

    /**
     * Show.
     * @throws Exception When can't.
     */
    @Test
    @Disabled
    public void show() throws Exception {
        final Proc<Iterable<DbObject<YamlNode>>> print = objects -> {
            System.out.println(
                new Repeated("--  ", 10).asString()
            );
            System.out.println(
                new Joined(
                    new Newline(),
                    new MappedWithIndex<Text>(
                        (object, i) -> new TextOfMessageFormat(
                            "{0}. {1}",
                            String.valueOf(i),
                            object.signature().asString()
                        ),
                        objects
                    )
                ).asString()
            );
        };
        final Iterable<DbObject<YamlNode>> objects = new Sticky<>(
            new ObjectsOfServer(
                new DbdServerMappingWithCredentials(
                    new UrlOfPgGitLabDatabaseV11("pagilla"),
                    new CredsOfPgTestDatabase()
                )
            )
        );
        final Iterable<DbObject<YamlNode>> updated = new Sticky<>(
            new ObjectsWithTypeAndNameMatchesRegexp<>(
                new TextOf("table"),
                new TextOf(".*payment_p2020_05"),
                objects
            )
        );
        final Iterable<DbObject<YamlNode>> deps = new Sticky<>(
            new ObjectsOfOneAreDependenciesOfAnotherRecursive<>(
                objects,
                updated
            )
        );
        print.exec(updated);
        print.exec(deps);
    }

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
            new ObjectsWithTypeAndNameMatchesRegexp<>(
                new TextOf("table"),
                new TextOf(".*rental"),
                all
            )
        );
        final Iterable<DbObject<YamlNode>> deps = new Sticky<>(
            new ObjectsOfOneAreDependenciesOfAnotherRecursive<>(
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
                    new SimpleObjectName("public", "customer"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "address"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "city"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "country"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "staff"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "store"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "inventory"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "film"),
                    new ObjectTypeTable()
                ),
                new SimpleObjectSignature(
                    new SimpleObjectName("public", "language"),
                    new ObjectTypeTable()
                )
            )
        ).affirm();
    }

}
