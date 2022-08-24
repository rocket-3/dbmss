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

import com.amihaiemil.eoyaml.YamlNode;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.database.snapshot.objects.SimpleDbObject;
import ru.fusionsoft.database.snapshot.objects.signature.SimpleObjectSignature;
import ru.fusionsoft.database.snapshot.objects.signature.name.ObjectSwappingEntityName;
import ru.fusionsoft.database.text.SwappingEntityNameSuffix;

/**
 * The {@link TableDropMigration}, but dropping {@link SwappingEntityNameSuffix}-named brother.
 * @since 0.1
 */
@SuppressWarnings("PMD")
public class TableDropSwappingEntityMigration extends TableDropMigration {

    /**
     * Instantiates a new Table drop swapping entity migration.
     * @param object The {@link DbObject} to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public TableDropSwappingEntityMigration(final DbObject<YamlNode> object, final Dbms dbms) {
        super(
            new SimpleDbObject<>(
                object.asYaml(),
                new SimpleObjectSignature(
                    new ObjectSwappingEntityName(object.signature().name()),
                    object.signature().type()
                )
            ),
            dbms
        );
    }

}
