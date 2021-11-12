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
package org.fusionsoft.database.snapshot.objects.ofdbms.postgres;

import com.amihaiemil.eoyaml.YamlMapping;
import java.sql.ResultSet;
import org.fusionsoft.database.mapping.entries.ScalarEntry;
import org.fusionsoft.database.mapping.fields.DbdSchemaFields;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.lib.text.TextOfResultSet;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

/**
 * The type of {@link org.fusionsoft.database.snapshot.DbObject}
 *  that can be constructed of {@link ResultSet}.
 * @since 0.1
 */
public class PostgresSchemaOfResultSet extends SimpleDbObject<YamlMapping> {

    /**
     * Instantiates a new Naive db object.
     * @param rset The ResultSet to be encapsulated.
     */
    public PostgresSchemaOfResultSet(final ResultSet rset) {
        super(
            new YamlMappingOfEntries(
                new ScalarEntry(
                    DbdSchemaFields.OWNER,
                    new TextOfResultSet("owner", rset)
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(
                    new TextOfResultSet("name", rset)
                ),
                ObjectType.SCHEMA
            )
        );
    }

}
