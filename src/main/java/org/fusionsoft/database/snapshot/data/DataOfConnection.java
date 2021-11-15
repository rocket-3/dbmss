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

import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.map.MapEntry;
import org.cactoos.text.TextOf;
import org.fusionsoft.database.mapping.dbd.DbdTableMapping;
import org.fusionsoft.database.mapping.fields.DbdTableFields;
import org.fusionsoft.database.snapshot.DbObject;
import org.fusionsoft.database.snapshot.objects.ObjectType;
import org.fusionsoft.database.snapshot.objects.SimpleDbObject;
import org.fusionsoft.database.snapshot.objectsignature.FullObjectName;
import org.fusionsoft.database.snapshot.objectsignature.SimpleObjectSignature;
import org.fusionsoft.database.snapshot.query.DataQuery;
import org.fusionsoft.lib.yaml.YamlMappingOfEntries;

public class DataOfConnection extends SimpleDbObject<YamlMapping> {

    public DataOfConnection(final Connection connection, final DbObject<DbdTableMapping> table) {
        super(
            new YamlMappingOfEntries(
                new IterableOf<Map.Entry<? extends Text, ? extends YamlNode>>(
                    () -> {
                        final Long rows = new RowsCount(table, connection).longValue();
                        final Statement stmt = connection.createStatement();
                        stmt.setFetchSize(5000);
                        final ResultSet rset = stmt.executeQuery(
                            new DataQuery(table).asString()
                        );
                        return new Iterator<Map.Entry<? extends Text, ? extends YamlNode>>() {
                            Long row = new Long(0);

                            @Override
                            public boolean hasNext() {
                                return row <= rows;
                            }

                            @Override
                            public Map.Entry<? extends Text, ? extends YamlNode> next() {
                                rset.next();
                                row++;
                                return new MapEntry<>(
                                    new TextOf(String.valueOf(row)),

                                )
                            }
                        };
                    }
                )
            ),
            new SimpleObjectSignature(
                new FullObjectName(
                    table.signature().name().parent(),
                    table.signature().name().first(),
                    DbdTableFields.DATA
                ),
                ObjectType.DATA
            )
        );
    }

}
