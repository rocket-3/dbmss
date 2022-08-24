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
package ru.fusionsoft.database.migration.common;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.Text;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.postgres.PgAnyObjectRenameSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The rename to one with {@link SwappingEntityNameSuffix} Migration,
 *  automatically picked up by object's type and DBMS specified.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines).
 */
public final class AnyObjectRenameMigration implements Migration {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<YamlNode> object;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * Instantiates a new Any object rename migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public AnyObjectRenameMigration(final DbObject<YamlNode> object, final Dbms dbms) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public Text description() {
        return new TextOfMessageFormat(
            "Renaming object {0} to {1}{2}",
            () -> this.object.signature().name(),
            () -> this.object.signature().name(),
            SwappingEntityNameSuffix::new
        );
    }

    @Override
    public Text sql() {
        return new TextOfDbmsConditional(
            new PgAnyObjectRenameSql(
                this.object,
                new Joined(
                    new TextOf(""),
                    this.object.signature().name().first(),
                    new SwappingEntityNameSuffix()
                )
            ),
            () -> "",
            () -> "",
            () -> "",
            this.dbms
        );
    }

}
