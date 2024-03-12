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
package ru.fusionsoft.database.migration.common;

import org.cactoos.Scalar;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.migration.CreateMigrationForObjectTypeMapEntry;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.MigrationOfScalar;
import ru.fusionsoft.database.migration.common.sqlentities.FunctionCreateMigration;
import ru.fusionsoft.database.migration.common.sqlentities.ProcedureCreateMigration;
import ru.fusionsoft.database.migration.common.sqlentities.TriggerCreateMigration;
import ru.fusionsoft.database.migration.common.sqlentities.ViewCreateMigration;
import ru.fusionsoft.database.migration.common.udt.DomainCreateMigration;
import ru.fusionsoft.database.migration.common.udt.EnumCreateMigration;
import ru.fusionsoft.database.migration.common.udt.TupleCreateMigration;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.signature.ObjectType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeConstraint;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeDomain;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeEnum;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeFunction;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeProcedure;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSchema;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeSequence;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTable;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTrigger;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeTuple;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeView;

/**
 * The create Migration, automatically picked up by object's type and DBMS specified.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines).
 */
public class AnyObjectCreateMigration extends MigrationOfScalar {

    /**
     * Instantiates a new Any object create migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public AnyObjectCreateMigration(final DbObject<?> object, final Dbms dbms) {
        super(
            () -> {
                return new MapOf<ObjectType<?>, Scalar<Migration>>(
                    new Mapped<>(
                        x -> x.apply(object, dbms),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeSchema(),
                            SchemaCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeTable(),
                            TableCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeSequence(),
                            SequenceCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeEnum(),
                            EnumCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeTuple(),
                            TupleCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeDomain(),
                            DomainCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeProcedure(),
                            ProcedureCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeFunction(),
                            FunctionCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeView(),
                            ViewCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeTrigger(),
                            TriggerCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeConstraint(),
                            ConstraintCreateMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeIndex(),
                            IndexCreateMigration::new
                        )
                    )
                )
                    .get(object.signature().type())
                    .value();
            }
        );
    }

}
