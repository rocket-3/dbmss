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
package ru.fusionsoft.database.migration.typecasts;

import com.amihaiemil.eoyaml.YamlNode;
import java.sql.Connection;
import org.cactoos.scalar.Unchecked;
import org.cactoos.text.TextOf;
import ru.fusionsoft.database.migration.common.AnyObjectCreateMigration;
import ru.fusionsoft.database.migration.common.AnyObjectDropMigration;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.typecasts.sql.TypecastCheckSql;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.dbms.DbmsOfConnection;
import ru.fusionsoft.database.snapshot.objects.ObjectWithNameSuffix;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;
import ru.fusionsoft.lib.connection.BooleanOfConnectionExecution;

/**
 * The {@link Typecast} made of UDT's {@link DbObject}s {@link TemporalDiff}.
 * @since 0.1
 */
public class TypecastOfUdtDiffAndConnection extends SimpleTypecast {

    /**
     * Instantiates a new Typecast of udt diff and connection.
     * @param diff The {@link TemporalDiff} of {@link DbObject}s of UDT to be encapsulated.
     * @param connection The {@link Connection} to be encapsulated.
     */
    public TypecastOfUdtDiffAndConnection(
        final TemporalDiff<DbObject<YamlNode>> diff,
        final Connection connection
    ) {
        super(
            new ObjectWithNameSuffix(diff.current(), new SwappingEntityNameSuffix())
                .signature().name().first(),
            diff.next()
                .signature().name().first(),
            new Unchecked<>(
                () -> {
                    final DbmsOfConnection dbms = new DbmsOfConnection(connection);
                    final SimpleDbObject<YamlNode> udtnew = new ObjectWithNameSuffix(
                        diff.next(),
                        new TextOf("_udt_typecast_check")
                    );
                    new BooleanOfConnectionExecution(
                        connection,
                        conn -> conn.createStatement().execute(
                            new AnyObjectDropMigration(
                                udtnew,
                                dbms
                            ).sql().asString()
                        )
                    ).value();
                    new BooleanOfConnectionExecution(
                        connection,
                        conn -> conn.createStatement().execute(
                            new AnyObjectCreateMigration(
                                udtnew,
                                dbms
                            ).sql().asString()
                        )
                    ).value();
                    final Boolean value = new BooleanOfConnectionExecution(
                        connection,
                        conn -> conn.createStatement().execute(
                            new TypecastCheckSql(
                                diff.current().signature().name().first(),
                                udtnew.signature().name().first(),
                                dbms
                            ).asString()
                        )
                    ).value();
                    new BooleanOfConnectionExecution(
                        connection,
                        conn -> conn.createStatement().execute(
                            new AnyObjectDropMigration(
                                udtnew,
                                dbms
                            ).sql().asString()
                        )
                    ).value();
                    return value;
                }
            ).value()
        );
    }

}
