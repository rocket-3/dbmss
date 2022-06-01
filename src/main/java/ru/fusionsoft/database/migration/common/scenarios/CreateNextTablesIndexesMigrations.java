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
package ru.fusionsoft.database.migration.common.scenarios;

import com.amihaiemil.eoyaml.YamlNode;
import org.cactoos.iterable.Mapped;
import ru.fusionsoft.database.diff.TemporalDiff;
import ru.fusionsoft.database.migration.Migration;
import ru.fusionsoft.database.migration.common.IndexCreateMigration;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.filtered.ObjectsWithType;
import ru.fusionsoft.database.snapshot.objects.signature.type.ObjectTypeIndex;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

public class CreateNextTablesIndexesMigrations extends IterableOfScalarSticky<Migration> {

    /**
     * Ctor.
     */
    public CreateNextTablesIndexesMigrations(
        final TemporalDiff<Iterable<DbObject<YamlNode>>> diff,
        final Dbms dbms
    ) {
        super(
            () -> {
                return new Mapped<>(
                    ind -> new IndexCreateMigration(ind, dbms),
                    new ObjectsWithType<>(
                        new ObjectTypeIndex(),
                        diff.next()
                    )
                );
            }
        );
    }

}
