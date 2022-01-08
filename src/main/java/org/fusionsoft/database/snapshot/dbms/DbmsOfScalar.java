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
package org.fusionsoft.database.snapshot.dbms;

import com.amihaiemil.eoyaml.YamlMapping;
import java.sql.Connection;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.NoNulls;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.database.snapshot.Dbms;
import org.fusionsoft.database.snapshot.Objects;

/**
 * The {@link Dbms} that can be constructed of {@link Scalar} of {@link Dbms}.
 * @since 0.1
 */
public class DbmsOfScalar implements Dbms {

    /**
     * The Unchecked scalar of Dbms encapsulated.
     */
    private final Unchecked<Dbms> scalar;

    /**
     * Instantiates a new Dbms of scalar.
     * @param scalar The Scalar of Dbms to be encapsulated.
     */
    public DbmsOfScalar(final Scalar<Dbms> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(new NoNulls<>(scalar)));
    }

    @Override
    public final Text dbd() {
        return this.scalar.value().dbd();
    }

    @Override
    public final Text driver() {
        return this.scalar.value().driver();
    }

    @Override
    public final Func<Connection, Objects<YamlMapping>> objects() {
        return this.scalar.value().objects();
    }

}
