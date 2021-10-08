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
import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.fusionsoft.database.snapshot.objects.ObjectsFromPostgres;

/**
 * The DbmsSignatureName that can be constructed of Text.
 * @since 0.1
 */
public class DbmsSignatureKindOfText extends DbmsSignatureKind {

    /**
     * Instantiates a new Dbms signature name.
     * @param text The String of name to be encapsulated.
     */
    public DbmsSignatureKindOfText(final Text text) {
        super(
            text,
            (Connection conn) -> {
                return new MapOf<>(
                    new MapEntry<>(
                        "POSTGRES", new ObjectsFromPostgres(conn)
                    ),
                    new MapEntry<>(
                        "ORACLE", new ObjectsFromPostgres(conn)
                    ),
                    new MapEntry<>(
                        "MSSQL", new ObjectsFromPostgres(conn)
                    ),
                    new MapEntry<>(
                        "MYSQL", new ObjectsFromPostgres(conn)
                    )
                ).get(text.asString());
            }
        );
    }

}
