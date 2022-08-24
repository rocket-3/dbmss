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
package ru.fusionsoft.database.migration.common.udt;

import org.cactoos.Text;
import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.postgres.PgAnyObjectRenameSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.signature.name.ObjectSwappingEntityName;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.database.text.TextOfDbmsConditional;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The Migration to rename enum of enum {@link DbObject} and DBMS specified
 *  to {@link SwappingEntityNameSuffix}-version.
 * @since 0.1
 */
public class EnumRenameMigration implements Migration {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<DbdDomainMapping> object;

    /**
     * The Dbms encapsulated.
     */
    private final Dbms dbms;

    /**
     * Instantiates a new Domain merge migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public EnumRenameMigration(final DbObject<DbdDomainMapping> object, final Dbms dbms) {
        this.object = object;
        this.dbms = dbms;
    }

    @Override
    public final Text description() {
        return new TextOfMessageFormat(
            "Renaming enum {0} to {0}{1}",
            () -> this.object.signature().name(),
            () -> new SwappingEntityNameSuffix()
        );
    }

    @Override
    public final Text sql() {
        return new TextOfDbmsConditional(
            new PgAnyObjectRenameSql(
                this.object,
                new ObjectSwappingEntityName(
                    this.object.signature().name()
                )
            ),
            () -> "",
            () -> "",
            () -> "",
            this.dbms
        );
    }

}

