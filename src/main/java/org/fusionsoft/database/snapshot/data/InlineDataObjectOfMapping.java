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
package org.fusionsoft.database.snapshot.data;

import org.fusionsoft.database.mapping.dbd.DbdDataMapping;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.objects.signature.name.SimpleObjectName;
import org.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeData;

public class InlineDataObjectOfMapping extends SimpleDbObject<DbdDataMapping> {

    public InlineDataObjectOfMapping(
        final DbdDataMapping mapping,
        final DbObject<DbdTableMapping> table
    ) {
        super(
            mapping,
            new SimpleObjectSignature(
                new SimpleObjectName(
                    table.signature().name().parent(),
                    table.signature().name().first(),
                    DbdTableFields.DATA
                ),
                new ObjectTypeData()
            )
        );
    }

}