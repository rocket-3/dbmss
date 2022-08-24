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
import org.cactoos.text.Newline;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The Migration to rename UDT to {@link SwappingEntityNameSuffix}-version
 *  and creating new with target state.
 * @since 0.1
 */
public class UdtPrepareToMergeMigration implements Migration {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<YamlNode> object;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * Instantiates a new Udt prepare to merge migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     * @checkstyle StringLiteralsConcatenationCheck (100 lines).
     */
    public UdtPrepareToMergeMigration(final DbObject<YamlNode> object, final Dbms dbms) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Merging '{0}' by renaming the old to '{1}.{2}{3}'"
            + " and creating the new version as {1}.{2}",
            () -> this.object.signature().type(),
            () -> this.object.signature().name().parent(),
            () -> this.object.signature().name().first(),
            () -> new SwappingEntityNameSuffix()
        );
    }

    @Override
    public final Text sql() {
        return new Joined(
            new Newline(),
            new AnyObjectRenameMigration(this.object, this.dbms).sql(),
            new AnyObjectCreateMigration(this.object, this.dbms).sql()
        );
    }

}
