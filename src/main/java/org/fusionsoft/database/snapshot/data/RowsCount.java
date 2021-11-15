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
package org.fusionsoft.database.snapshot.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import org.cactoos.Text;
import org.cactoos.scalar.NumberEnvelope;
import org.cactoos.text.TextOfScalar;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;

public class RowsCount extends NumberEnvelope {

    public RowsCount(final Text schema, final Text table, final Connection connection) {
        super(
            () -> {
                try (
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(
                        MessageFormat.format(
                            "SELECT COUNT(*) FROM {0}.{1}",
                            schema,
                            table
                        )
                    )
                ) {
                    rs.next();
                    return Double.parseDouble(
                        MessageFormat.format(
                            "{0}.0",
                            rs.getString(1)
                        )
                    );
                }
            }
        );
    }

    public RowsCount(final FullObjectName table, final Connection connection) {
        this(
            new TextOfScalar(() -> table.parent().asString()),
            new TextOfScalar(() -> table.first().asString()),
            connection
        );
    }

    public RowsCount(final DbObject<?> table, final Connection connection) {
        this(
            new TextOfScalar(() -> table.signature().name().parent().asString()),
            new TextOfScalar(() -> table.signature().name().first().asString()),
            connection
        );
    }

}
