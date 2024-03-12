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
package ru.fusionsoft.database.migration.typecasts.sql;

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextEnvelope;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.dbms.PostgresDbms;
import ru.fusionsoft.lib.collection.StrictMap;

/**
 * SQL code for given DBMS to check if possible to cast one type to another.
 * @since 0.1
 */
public class TypecastCheckSql extends TextEnvelope {

    /**
     * Instantiates a new Pg typecast check sql.
     * @param source From type text name.
     * @param target To type text name.
     * @param dbms The {@link Dbms} encapsulated.
     */
    public TypecastCheckSql(final Text source, final Text target, final Dbms dbms) {
        super(
            new Unchecked<Text>(
                () -> {
                    return new StrictMap<>(
                        new MapOf<Dbms, Text>(
                            new MapEntry<>(
                                new PostgresDbms(),
                                new PgTypecastCheckSql(source, target)
                            )
                        )
                    ).get(dbms);
                }
            ).value()
        );
    }

}
