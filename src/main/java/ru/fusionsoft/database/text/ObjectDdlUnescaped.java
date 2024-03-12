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

import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOfString;
import ru.fusionsoft.database.mapping.fields.DbdViewFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.TextOfObjectField;

/**
 * Deletes leading comments in sql text.
 * @since 0.1
 */
public class ObjectDdlUnescaped extends TextEnvelope {

    /**
     * Instantiates a new Object ddl unescaped.
     * @param object The database object
     */
    public ObjectDdlUnescaped(final DbObject<?> object) {
        super(
            new TextOfString(
                new TextOfObjectField(
                    object,
                    DbdViewFields.DDL
                ).asString().replace(
                    "/**/",
                    ""
                )
            )
        );
    }

}
