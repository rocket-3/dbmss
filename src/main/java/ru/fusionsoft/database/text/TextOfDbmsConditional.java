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
package ru.fusionsoft.database.text;

import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.text.UncheckedText;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.dbms.MsSqlDbms;
import ru.fusionsoft.database.snapshot.dbms.MySqlDbms;
import ru.fusionsoft.database.snapshot.dbms.OracleDbms;
import ru.fusionsoft.database.snapshot.dbms.PostgresDbms;
import ru.fusionsoft.lib.collection.StrictMap;

public class TextOfDbmsConditional implements Text {

    private final Map<Dbms, Text> map;

    private final Dbms dbms;

    public TextOfDbmsConditional(
        final Iterable<Map.Entry<? extends Dbms, ? extends Text>> entries,
        final Dbms dbms
    ) {
        this.map = new StrictMap<>(new MapOf<>(entries));
        this.dbms = dbms;
    }

    @SafeVarargs
    public TextOfDbmsConditional(final Dbms dbms, final MapEntry<Dbms, Text>... entries) {
        this(new IterableOf<>(entries), dbms);
    }

    public TextOfDbmsConditional(
        final Text postgres,
        final Text mssql,
        final Text mysql,
        final Text oracle,
        final Dbms dbms
    ) {
        this(
            dbms,
            new MapEntry<>(
                new PostgresDbms(),
                postgres
            ),
            new MapEntry<>(
                new MsSqlDbms(),
                mssql
            ),
            new MapEntry<>(
                new MySqlDbms(),
                mysql
            ),
            new MapEntry<>(
                new OracleDbms(),
                oracle
            )
        );
    }

    @Override
    public String asString() {
        return new UncheckedText(
            this.map.get(this.dbms)
        ).asString();
    }

}
