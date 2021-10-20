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
package org.fusionsoft.database.snapshot.dbmssignature;

import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.func.UncheckedFunc;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.dbms.ObjectsFromMssql;
import org.fusionsoft.database.snapshot.objects.dbms.ObjectsFromMySql;
import org.fusionsoft.database.snapshot.objects.dbms.ObjectsFromOracle;
import org.fusionsoft.database.snapshot.objects.dbms.ObjectsFromPostgres;

/**
 * The DbmsSignatureName representing DBMS type related behavior.
 * @since 0.1
 */
public enum DbmsType implements Text {
    /**
     *Postgres dbms type.
     */
    POSTGRES("POSTGRES", ObjectsFromPostgres::new),
    /**
     *Mssql dbms type.
     */
    MSSQL("MSSQL", ObjectsFromMssql::new),
    /**
     *Mysql dbms type.
     */
    MYSQL("MYSQL", ObjectsFromMySql::new),
    /**
     *Oracle dbms type.
     */
    ORACLE("ORACLE", ObjectsFromOracle::new);

    /**
     * The String encapsulated.
     */
    private final String text;

    /**
     * The UncheckedFunc of Connection returns Objects to be encapsulated.
     */
    private final UncheckedFunc<Connection, Objects> func;

    /**
     * Instantiates a new Dbms type.
     * @param text The String to be encapsulated.
     * @param func The Func of Connection returns Objects to be encapsulated.
     */
    DbmsType(final String text, final Func<Connection, Objects> func) {
        this.text = text;
        this.func = new UncheckedFunc<>(func);
    }

    /**
     * Objects of {@link Connection} function.
     * @return The function.
     */
    public final Func<Connection, Objects> objects() {
        return this.func;
    }

    @Override
    public final String asString() {
        return this.text;
    }
}
