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

import java.util.Map;
import org.cactoos.Scalar;
import org.cactoos.iterable.Mapped;
import org.cactoos.map.MapOf;
import ru.fusionsoft.database.migration.CreateMigrationForObjectTypeMapEntry;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.MigrationOfScalar;
import ru.fusionsoft.database.migration.common.sqlentities.FunctionDropMigration;
import ru.fusionsoft.database.migration.common.sqlentities.ProcedureDropMigration;
import ru.fusionsoft.database.migration.common.sqlentities.TriggerDropMigration;
import ru.fusionsoft.database.migration.common.sqlentities.ViewDropMigration;
import ru.fusionsoft.database.migration.common.udt.DomainDropMigration;
import ru.fusionsoft.database.migration.common.udt.EnumDropMigration;
import ru.fusionsoft.database.migration.common.udt.TupleDropMigration;
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
 * The drop Migration, automatically picked up by object's type and DBMS specified.
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (100 lines).
 */
public class AnyObjectDropMigration extends MigrationOfScalar {

    /**
     * Instantiates a new Any object drop migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public AnyObjectDropMigration(final DbObject<?> object, final Dbms dbms) {
        super(
            () -> {
                return new MapOf<ObjectType<?>, Scalar<Migration>>(
                    new Mapped<Map.Entry<? extends ObjectType<?>, ? extends Scalar<Migration>>>(
                        x -> x.apply(object, dbms),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeSchema(),
                            SchemaDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeTable(),
                            TableDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeSequence(),
                            SequenceDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeEnum(),
                            EnumDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeTuple(),
                            TupleDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeDomain(),
                            DomainDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeProcedure(),
                            ProcedureDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeFunction(),
                            FunctionDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeView(),
                            ViewDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeTrigger(),
                            TriggerDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeConstraint(),
                            ConstraintDropMigration::new
                        ),
                        new CreateMigrationForObjectTypeMapEntry<>(
                            new ObjectTypeIndex(),
                            IndexDropMigration::new
                        )
                    )
                ).get(object.signature().type()).value();
            }
        );
    }

}
