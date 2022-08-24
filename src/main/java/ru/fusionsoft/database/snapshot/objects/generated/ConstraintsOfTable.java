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
package ru.fusionsoft.database.snapshot.objects.generated;

import ru.fusionsoft.database.mapping.dbd.DbdDomainMapping;
import ru.fusionsoft.database.mapping.dbd.DbdTableMapping;
import ru.fusionsoft.database.mapping.fields.DbdTableFields;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.ofdbd.ObjectsOfConstraintsMapping;
import ru.fusionsoft.database.snapshot.objects.signature.name.ObjectNameOfScalar;
import ru.fusionsoft.lib.yaml.YamlMappingOfScalar;

/**
 * Constraints {@link DbObject}'s extracted of {@link DbdDomainMapping} {@link DbObject}.
 * @since 0.1
 */
public class ConstraintsOfTable extends ObjectsOfConstraintsMapping {

    /**
     * Instantiates a new Constraints of table.
     * @param table The {@link DbObject} of {@link DbdDomainMapping} to be encapsulated.
     */
    public ConstraintsOfTable(final DbObject<DbdTableMapping> table) {
        super(
            new YamlMappingOfScalar(table::asYaml),
            DbdTableFields.CONSTRAINTS,
            new ObjectNameOfScalar(() -> table.signature().name())
        );
    }

}
