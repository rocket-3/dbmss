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

package ru.fusionsoft.database.snapshot.objects;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.Scalar;
import org.cactoos.Text;
import ru.fusionsoft.database.snapshot.DbObject;

public class ObjectFieldExistence implements Scalar<Boolean> {

    private final TextOfObjectFieldMaybeEmpty field;

    public ObjectFieldExistence(final DbObject<? extends YamlMapping> object, final Text field) {
        this.field = new TextOfObjectFieldMaybeEmpty(object, field);
    }

    @Override
    public Boolean value() {
        return !field.asString().isEmpty();
    }

}
