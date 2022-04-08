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
package ru.fusionsoft.database.snapshot.objects.dependencies;

import java.util.regex.Pattern;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;
import ru.fusionsoft.database.mapping.fields.DbdFunctionFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.ObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectName;
import ru.fusionsoft.database.snapshot.objects.signature.name.ObjectNameOfScalar;

public class ObjectHasNameInSqlPredicate implements Func<DbObject<?>, Boolean> {

    private final Unchecked<Pattern> pattern;

    private ObjectHasNameInSqlPredicate(final Scalar<Pattern> pattern) {
        this.pattern = new Unchecked<>(new Sticky<>(pattern));
    }

    public ObjectHasNameInSqlPredicate(final ObjectName name) {
        this(
            () -> Pattern.compile("\\b[a-zA-Z0-9\\.]?" + name.first().asString())
        );
    }

    public ObjectHasNameInSqlPredicate(final ObjectSignature signature) {
        this(
            new ObjectNameOfScalar(signature::name)
        );
    }

    public ObjectHasNameInSqlPredicate(final DbObject<?> object) {
        this(
            new ObjectNameOfScalar(
                () -> object.signature().name()
            )
        );
    }

    @Override
    public Boolean apply(final DbObject<?> input) {
        return new ObjectFieldMapped<>(
            input,
            DbdFunctionFields.DDL,
            node -> this.pattern.value().matcher(node.asScalar().value()).find(),
            () -> false
        ).value();
    }

}
