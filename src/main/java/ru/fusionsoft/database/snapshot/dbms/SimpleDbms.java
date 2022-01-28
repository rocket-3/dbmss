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

import com.amihaiemil.eoyaml.YamlMapping;
import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.Text;
import org.cactoos.func.UncheckedFunc;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.Objects;

/**
 * The basic Dbms implementation.
 * @since 0.1
 */
public class SimpleDbms implements Dbms {

    /**
     * The Text of dbd label encapsulated.
     */
    private final Text dbdtext;

    /**
     * The Text of driver dbms name label encapsulated.
     */
    private final Text drivertext;

    /**
     * The The Func of Connection returns Objects to be encapsulated.
     */
    private final Func<Connection, Objects<YamlMapping>> objectsf;

    /**
     * Instantiates a new Any dbms.
     * @param dbd The String of DBD label to be encapsulated.
     * @param driver The String of driver dbms label to be encapsulated.
     * @param objects The Func of Connection returns Objects to be encapsulated.
     */
    public SimpleDbms(
        final String dbd,
        final String driver,
        final Func<Connection, Objects<YamlMapping>> objects
    ) {
        this(new TextOf(dbd), new TextOf(driver), objects);
    }

    /**
     * Instantiates a new Any dbms.
     * @param dbd The Text of DBD label to be encapsulated.
     * @param driver The Text of driver dbms label to be encapsulated.
     * @param objects The Func of Connection returns Objects to be encapsulated.
     */
    public SimpleDbms(
        final Text dbd,
        final Text driver,
        final Func<Connection, Objects<YamlMapping>> objects
    ) {
        this.dbdtext = dbd;
        this.drivertext = driver;
        this.objectsf = objects;
    }

    @Override
    public final Text dbd() {
        return this.dbdtext;
    }

    @Override
    public final Text driver() {
        return this.drivertext;
    }

    @Override
    public final Objects<YamlMapping> objects(final Connection connection) {
        return new UncheckedFunc<>(this.objectsf).apply(connection);
    }

}
