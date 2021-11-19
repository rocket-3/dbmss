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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.cactoos.Text;
import org.cactoos.scalar.Unchecked;
import org.fusionsoft.lib.exception.NotImplemented;

public class ValueFormatDummy implements ValueFormat {

    @Override
    public Text storableRepresentationOf(final Text text) {
        return text;
    }

    @Override
    public void passInPrepareStatement(
        final PreparedStatement stmt,
        final int ordinal,
        final Text text
    ) {
        throw new NotImplemented();
    }

    @Override
    public String ofResultSet(final ResultSet rset, final int ordinal) {
        return new Unchecked<>(() -> String.valueOf(rset.getString(ordinal))).value();
    }

    @Override
    public String ofResultSet(final ResultSet rset, final String key) {
        return new Unchecked<>(() -> String.valueOf(rset.getString(key))).value();
    }

}
