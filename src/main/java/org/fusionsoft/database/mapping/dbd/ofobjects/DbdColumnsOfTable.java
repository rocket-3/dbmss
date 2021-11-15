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

import com.amihaiemil.eoyaml.YamlMapping;
import org.fusionsoft.database.mapping.MappingOfRepresentative;
import org.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.lib.yaml.YamlNodeOfPath;
import org.fusionsoft.lib.yaml.artefacts.ValuesOfYamlSequence;

public class DbdColumnsOfTable extends ValuesOfYamlSequence<DbdColumnMapping> {

    public DbdColumnsOfTable(final DbObject<DbdTableMapping> object) {
        this(new MappingOfRepresentative(object));
    }

    private DbdColumnsOfTable(final YamlMapping mapping) {
        this(new DbdTableMapping(mapping));
    }

    public DbdColumnsOfTable(final DbdTableMapping mapping) {
        super(
            new YamlNodeOfPath(mapping, DbdTableFields.COLUMNS),
            x -> new DbdColumnMapping(x.asMapping())
        );
    }

}
