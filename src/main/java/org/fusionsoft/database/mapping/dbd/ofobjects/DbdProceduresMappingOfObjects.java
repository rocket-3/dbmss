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
package org.fusionsoft.database.mapping.dbd.ofobjects;

import org.fusionsoft.database.mapping.dbd.DbdProcedureMapping;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.Objects;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * The type of {@link DbdProcedureMapping} that can be extracted
 *  of all {@link Objects} context and parent schema {@link DbObject}.
 * @since 0.1
 */
public class DbdProceduresMappingOfObjects extends YamlMappingOfScalar {

    /**
     * Instantiates a new Dbd procedures mapping of objects.
     * @param objects The Objects to be encapsulated.
     * @param parent The DbObject to be encapsulated.
     */
    public DbdProceduresMappingOfObjects(final Objects objects, final DbObject<?> parent) {
        super(
            () -> new DbdMappingOfObjectsBiFunc<>(
                ObjectType.PROCEDURE,
                DbdProcedureMapping::new
            ).apply(objects, parent)
        );
    }

}
