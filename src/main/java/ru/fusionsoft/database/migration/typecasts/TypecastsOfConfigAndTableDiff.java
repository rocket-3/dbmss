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
import ru.fusionsoft.database.mapping.config.MigrationConfigMapping;
import ru.fusionsoft.database.migration.diff.TemporalDiff;
import ru.fusionsoft.database.migration.typecasts.typediff.TypeDiffsOfObjectsDiff;
import ru.fusionsoft.database.snapshot.DbObject;
import ru.fusionsoft.database.snapshot.Dbms;
import ru.fusionsoft.lib.collection.IterableOfScalarSticky;

/**
 * The {@link Typecast} made of table's {@link DbObject}s {@link TemporalDiff}.
 * @since 0.1
 */
public class TypecastsOfConfigAndTableDiff extends IterableOfScalarSticky<Typecast> {

    /**
     * Instantiates a new Typecasts of config and table diff.
     * @param config The {@link MigrationConfigMapping} to be encapsulated.
     * @param diff The {@link TemporalDiff} of {@link DbObject}s of tables to be encapsulated.
     * @param dbms The {@link Dbms} to be encapsulated.
     */
    public TypecastsOfConfigAndTableDiff(
        final MigrationConfigMapping config,
        final TemporalDiff<DbObject<YamlNode>> diff,
        final Dbms dbms
    ) {
        super(
            () -> new TypecastsOfTypeDiffsAndConfig(
                new TypeDiffsOfObjectsDiff(diff),
                new TypecastsOfConfigAndDbms(config, dbms)
            )
        );
    }

}
