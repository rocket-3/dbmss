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

package ru.fusionsoft.database.snapshot.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * The type of {@link ValueFormat} that uses {@link Scalar} of an another as delegate.
 * @since 0.1
 */
public class ValueFormatOfScalar implements ValueFormat {

    /**
     * The {@link Unchecked} of {@link ValueFormat} encapsulated.
     */
    private final Unchecked<? extends ValueFormat> scalar;

    /**
     * Instantiates a new Value format of scalar.
     * @param scalar The {@link Scalar of ValueFormat} to be encapsulated.
     */
    public ValueFormatOfScalar(final Scalar<? extends ValueFormat> scalar) {
        this.scalar = new Unchecked<>(new Sticky<>(scalar));
    }

    @Override
    public final String yamlRepresentationOf(final String text) {
        return this.scalar.value().yamlRepresentationOf(text);
    }

    @Override
    public final void passInPrepareStatement(
        final PreparedStatement stmt,
        final int ordinal,
        final Text text
    ) {
        this.scalar.value().passInPrepareStatement(stmt, ordinal, text);
    }

    @Override
    public final String ofResultSet(final ResultSet rset, final int ordinal) {
        return this.scalar.value().ofResultSet(rset, ordinal);
    }

    @Override
    public final String ofResultSet(final ResultSet rset, final String key) {
        return this.scalar.value().ofResultSet(rset, key);
    }

}
