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
package ru.fusionsoft.database.mapping.dbd.ofobjects;

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.mapping.dbd.DbdProcedureMapping;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;

/**
 * The type of {@link DbdProcedureMapping} that can be extracted
 *  of all {@link Iterable} of {@link DbObject}s context and parent schema {@link DbObject}.
 * @since 0.1
 */
public class DbdProceduresMappingOfObjects
    extends MappingOfObjectsOfParentAndType<DbdProcedureMapping> {

    /**
     * Instantiates a new Dbd procedures mapping of objects.
     * @param objects The Objects to be encapsulated.
     * @param schema The DbObject to be encapsulated.
     * @param <Y> The type of YamlNode parameter.
     */
    public <Y extends YamlNode> DbdProceduresMappingOfObjects(
        final Iterable<DbObject<Y>> objects,
        final DbObject<?> schema
    ) {
        super(
            objects,
            schema,
            new ObjectTypeProcedure()
        );
    }

}
