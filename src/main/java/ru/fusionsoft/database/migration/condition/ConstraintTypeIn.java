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
package ru.fusionsoft.database.migration.condition;

import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.Mapped;
import org.cactoos.set.SetOf;
import ru.fusionsoft.database.mapping.dbd.DbdConstraintMapping;
import ru.fusionsoft.database.mapping.fields.DbdConstraintFields;
import ru.fusionsoft.database.mapping.values.ConstraintTypeValues;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectFieldMaybeEmpty;

/**
 * The predicate {@link Scalar} checks given constraint {@link DbObject}
 *  has given {@link ConstraintTypeValues}.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class ConstraintTypeIn implements Scalar<Boolean> {

    /**
     * The ConstraintTypeValues[] encapsulated.
     */
    private final ConstraintTypeValues[] types;

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<DbdConstraintMapping> object;

    /**
     * Instantiates a new Constraint type in.
     * @param object The {@link DbObject} of constraint to be encapsulated.
     * @param types The {@link ConstraintTypeValues}... to be encapsulated.
     */
    public ConstraintTypeIn(
        final DbObject<DbdConstraintMapping> object,
        final ConstraintTypeValues... types
    ) {
        this.types = types;
        this.object = object;
    }

    @Override
    public final Boolean value() {
        return new SetOf<>(new Mapped<>(Text::asString, this.types)).contains(
            new TextOfObjectFieldMaybeEmpty(this.object, DbdConstraintFields.TYPE).asString()
        );
    }

}
