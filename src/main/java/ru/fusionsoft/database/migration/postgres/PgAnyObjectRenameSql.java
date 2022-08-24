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
package ru.fusionsoft.database.migration.postgres;

import org.cactoos.Text;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.cactoos.scalar.Ternary;
import org.cactoos.text.TextOf;
import org.cactoos.text.TextOfString;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeDomain;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeEnum;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSequence;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTuple;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;
import ru.fusionsoft.database.text.PgProcedureArgs;
import ru.fusionsoft.lib.text.TextOfMessageFormat;

/**
 * The sql Text for Postgres DBMS to rename any given {@link DbObject} to some new name.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (200 lines).
 */
public class PgAnyObjectRenameSql implements Text {

    /**
     * The DbObject encapsulated.
     */
    private final DbObject<?> object;

    /**
     * The Text encapsulated.
     */
    private final Text newname;

    /**
     * Instantiates a new Pg any object rename sql.
     * @param object The {@link DbObject}.
     * @param newname The new name {@link Text}.
     */
    public PgAnyObjectRenameSql(final DbObject<?> object, final Text newname) {
        this.object = object;
        this.newname = newname;
    }

    @Override
    public final String asString() {
        final TextOfMessageFormat text = new TextOfMessageFormat(
            "ALTER {0} {1}.{2}{3} RENAME TO {4};",
            () -> new TextOfString(
                new MapOf<ObjectType<?>, String>(
                    new MapEntry<>(
                        new ObjectTypeTable(),
                        "TABLE"
                    ),
                    new MapEntry<>(
                        new ObjectTypeSequence(),
                        "SEQUENCE"
                    ),
                    new MapEntry<>(
                        new ObjectTypeEnum(),
                        "TYPE"
                    ),
                    new MapEntry<>(
                        new ObjectTypeDomain(),
                        "TYPE"
                    ),
                    new MapEntry<>(
                        new ObjectTypeTuple(),
                        "TYPE"
                    ),
                    new MapEntry<>(
                        new ObjectTypeProcedure(),
                        "PROCEDURE"
                    ),
                    new MapEntry<>(
                        new ObjectTypeFunction(),
                        "FUNCTION"
                    ),
                    new MapEntry<>(
                        new ObjectTypeView(),
                        "VIEW"
                    ),
                    new MapEntry<>(
                        new ObjectTypeTrigger(),
                        "TRIGGER"
                    )
                ).get(this.object.signature().type())
            ),
            () -> this.object.signature().name().parent().first(),
            () -> this.object.signature().name().first(),
            new Ternary<Text>(
                () -> new PgProcedureArgs(this.object).asString().isEmpty(),
                () -> new TextOf(""),
                () -> new TextOfMessageFormat(
                    "({0})",
                    new PgProcedureArgs(this.object)
                )
            ),
            () -> this.newname
        );
        return text.asString();
    }

}
