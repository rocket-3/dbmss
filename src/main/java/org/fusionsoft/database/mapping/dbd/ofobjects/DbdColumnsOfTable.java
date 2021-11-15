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

import com.amihaiemil.eoyaml.StrictYamlMapping;
import org.cactoos.iterable.IterableEnvelope;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterator.Mapped;
import org.fusionsoft.database.mapping.dbd.DbdColumnMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;

public class DbdColumnsOfTable extends IterableEnvelope<DbdColumnMapping> {

    public DbdColumnsOfTable(final DbObject<DbdTableMapping> object) {
        this(object.asYaml());
    }

    public DbdColumnsOfTable(final DbdTableMapping mapping) {
        super(
            new IterableOf<>(
                () -> new Mapped<>(
                    x -> new DbdColumnMapping(x.asMapping()),
                    new StrictYamlMapping(mapping)
                        .value(DbdTableFields.COLUMNS.asString())
                        .asSequence()
                        .iterator()
                )
            )
        );
    }

}
