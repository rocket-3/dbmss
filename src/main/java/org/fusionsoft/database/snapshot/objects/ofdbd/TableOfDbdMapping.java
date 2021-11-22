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
package org.fusionsoft.database.snapshot.objects.ofdbd;

import com.amihaiemil.eoyaml.YamlMapping;
import org.cactoos.iterable.IterableOf;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objects.signature.ObjectName;
import org.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import org.fusionsoft.lib.yaml.artefacts.MappingFromMappingIgnoreKeys;

public class TableOfDbdMapping extends SimpleDbObject<DbdTableMapping> {

    public TableOfDbdMapping(final YamlMapping mapping, final ObjectName table) {
        this(
            new DbdTableMapping(
                new MappingFromMappingIgnoreKeys(
                    mapping,
                    new IterableOf<>(
                        DbdTableFields.CONSTRAINTS,
                        DbdTableFields.INDEXES,
                        DbdTableFields.TRIGGERS
                    )
                )
            ),
            table
        );
    }

    /**
     * Instantiates a new simple db object.
     * @param mapping The {@link DbdTableMapping} to be encapsulated.
     * @param table The {@link ObjectName} to be encapsulated.
     */
    public TableOfDbdMapping(final DbdTableMapping mapping, final ObjectName table) {
        super(
            mapping,
            new SimpleObjectSignature(
                table,
                new ObjectTypeTable()
            )
        );
    }

}
